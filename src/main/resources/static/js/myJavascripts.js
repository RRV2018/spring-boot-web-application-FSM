/*$('.showRole').on('click',function(e){
    $('.modal-body').load('./showRoles?userId='+$(this).data("value"),function(){
        $('#showRoles').modal({show:true});
    });
});*/

$('.showRole').on('click',function(e){
 e.preventDefault();
    $.ajax({
            type: "POST",
            url: "/loadRoles",
            data: {
                userId: $(this).data("value")
            },
            success: function(result) {
            $("#roleTab > tbody").empty();
            for (var role of result) {
                var tr = "<tr><td align='center'>"+role.roleId+"</td><td>"+role.roleName+"</td><td>"+role.userName+"</td></tr>";
                $("#roleTab tbody").append(tr);
              }
              $('#showRoles').modal('show');
            },
            error: function(result) {
                alert('error');
                $('#showError').modal('show');
            }
        });
});
function validatePassword(){
var password = document.getElementById("myPassword")
  , confirm_password = document.getElementById("confirmPassword");

  if(password.value != confirm_password.value) {
    confirm_password.setCustomValidity("Passwords Don't Match");
  } else {
    confirm_password.setCustomValidity('');
  }
}