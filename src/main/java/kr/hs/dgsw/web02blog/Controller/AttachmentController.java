package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web02blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class AttachmentController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private CommentService commentService;

    @PostMapping("/attachment")
    public AttachmentProtocol upload(@RequestPart MultipartFile srcFile){
        String destFilename
                = "C:\\Users\\정광민\\ideaProjects\\web190326\\upload\\"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"))
                + UUID.randomUUID().toString() + "-"
                + srcFile.getOriginalFilename();
        try{
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            srcFile.transferTo(destFile);
            return new AttachmentProtocol(destFilename, srcFile.getOriginalFilename());
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping("/attachmentdown/{type}/{id}")
    public void download(@PathVariable String type, @PathVariable Long id, HttpServletRequest req, HttpServletResponse resp){
        try{
            String profilePath = null;
            if(type.equals("user")){
                User user = this.userService.view(id);
                profilePath = user.getProfilePath();
            }else{
//                Comment comment = this.commentService.view(id);
//                filePath = comment.getImgPath();
//                fileName = comment.getImgName();
            }

            File file = new File(profilePath);
            if(file.exists() == false) return;

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if(mimeType == null) mimeType = "application/octet-stream";

            resp.setContentType(mimeType);
            resp.setHeader("Content-Disposition", "inline; filename=\"" + profilePath + "\";");
            resp.setContentLength((int)file.length());

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is, resp.getOutputStream());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
