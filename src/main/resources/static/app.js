var Init = (async function getPost() {
    let response = await $.get('http://localhost:8080/post/list');
    console.log(response);
    if (response.code == 101) {
        ChangePost(response);

        $('#post-list').html('');
        for(let i = 0;i < response.length;i++){
            let post = response[i].data;
            $('#post-list').prepend(`
                <li onclick="ChangePost(${post.id})">${post.title}</li>
            `);
        }
    }
})();
let user;
(async function Login(id){
    let response = await $.get('http://localhost:8080/user/view/'+1);
    user = response;
    console.log(response);
    $('#user-img').html(`<img src="${response.data.profilePath}">`);
    $('#user-account').html(response.data.account);
    $('#user-name').html(response.data.name);
    $('#user-join').html(response.data.created);
    })()

{    let response = $.ajax({
    type:'GET',
    url:'/post/view/' + id
})
}
async function ChangePost(response){
    $('#post-title').html(response.data[0].title);
    $('#post-user-name').html(response.data[0].username);
    $('#post-date').html(response.data[0].created);
    $('#post-content').html(response.data[0].content);


    $('#prev').click(async function(){

    });
    $('#edit').click(function(){
        openEditDialog();
        $('#edit_OK').click(postEdit(response.data.id))
    });
    $('#remove').click(async function(){
        try{
            let response = await $.ajax({
                type: 'DELETE',
                url: '/post/delete/' + response.id
            });
        }catch (err) {

        }
    });
    $('#next').click(function(){

    });

}

async function addPost(){
    let newPost = {
        id : '',
        userId : user.data.userId,
        title : $('#add-post-title').val(),
        content: $('#add-post-content').val(),
        pictures : null
    }

    let response = await $.ajax({
        type:'POST',
        url:'/post/add',
        contentType:'application/json',
        data:JSON.stringify()
    });

    closePostDialog();

    Init;
}
async function postEdit(id){
    let updatePost = {
        id:'',
        userId: user.data.id,
        title: $('#edit-post-title').val(),
        content: $('#edit-post-content').val(),
        pictures : null
    }
    let response = await $.ajax({
        type:'PUT',
        url:'/post/update/' + id,
        contentType: 'application/json',
        data: JSON.stringify(updatePost)
    });
}
function openPostDialog() {
    $('#container').show(400);
}

function closePostDialog() {
    $('#container').hide(1000);
}

function openEditDialog() {
    $('#edit-post-title').html($('#post-title').val());
    $('#edit-post-content').html($('#post-content').val());
    $('#container_edit').show(400);
}

function closeEditDialog() {
    $('#container_edit').hide(1000);
}