$('document').ready(function () {
    $('.table .bth').click( function (e) {


        e.preventDefault();

        const href=$(this).attr('href');
        $.get(href,function (user,status) {
            $('#id_edit').val(user.id);
            $('#name_edit').val(user.name);
            $('#Last_Name_edit').val(user.Last_Name);
            $('#email_edit').val(user.email);
            $('#password_edit').val(user.password);
            $('#Role_edit').val(user.Role);

            
        })

        $('#Edit_Modal').show('modal');
    })

})