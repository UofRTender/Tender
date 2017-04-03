/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function showGroupButtons(info) {
    removeGroupButtons();
    var node = document.getElementById(info.id);

    var td1 = document.createElement("TD");
    td1.className = 'removeable';

    var editForm = document.createElement("FORM");
    editForm.method = 'POST';
    editForm.action = 'editGroup';


    var edithiddenInput = document.createElement("INPUT");
    edithiddenInput.name = 'gpk';
    edithiddenInput.value = info.id;
    edithiddenInput.type = 'hidden';

    var editInput = document.createElement("INPUT");
    editInput.value = 'edit group';
    editInput.type = 'submit';

    editForm.appendChild(edithiddenInput);
    editForm.appendChild(editInput);
    td1.appendChild(editForm);
    node.appendChild(td1);
    //table.appendChild(node);
    /*div.appendChild(table);
     out.appendChild(div);*/

    var td2 = document.createElement("TD");
    td2.className = 'removeable';

    var leaveForm = document.createElement("FORM");
    leaveForm.method = 'POST';
    leaveForm.action = 'viewGroup';


    var leavehiddenInput = document.createElement("INPUT");
    leavehiddenInput.name = 'gpk';
    leavehiddenInput.value = info.id;
    leavehiddenInput.type = 'hidden';

    var leaveInput = document.createElement("INPUT");
    leaveInput.value = 'leave group';
    leaveInput.type = 'submit';

    leaveForm.appendChild(leavehiddenInput);
    leaveForm.appendChild(leaveInput);
    td2.appendChild(leaveForm);
    node.appendChild(td2);
    //table.appendChild(node);
    /*div.appendChild(table);
     out.appendChild(div);*/

    var td3 = document.createElement("TD");
    td3.className = 'removeable';

    var viewForm = document.createElement("FORM");
    viewForm.method = 'GET';
    viewForm.action = 'viewGroup';


    var viewhiddenInput = document.createElement("INPUT");
    viewhiddenInput.name = 'id';
    viewhiddenInput.value = info.id;
    viewhiddenInput.type = 'hidden';

    var viewInput = document.createElement("INPUT");
    viewInput.value = 'view group';
    viewInput.type = 'submit';

    viewForm.appendChild(viewhiddenInput);
    viewForm.appendChild(viewInput);
    td3.appendChild(viewForm);
    node.appendChild(td3);
    //table.appendChild(node);
    //div.appendChild(table);
    //out.appendChild(div);
}

function removeGroupButtons() {
    var remove = document.getElementsByClassName('removeable');
    while (remove[0]) {
        remove[0].parentNode.removeChild(remove[0]);
    }
}

function removeFavourites(info) {
    var node = document.getElementById(info.id);
    $.get('removeFavourite',
            {
                place: node.id,
            },
            function (data) {
                console.log(data);
            });
}

function viewGroupHistory() {
    var id = document.getElementById("gname").value;
    var name = $('h1').html();
    $(location).attr('href', 'groupHistory?group_id=' + id + "&name=" + name);
    /*$.post('groupHistory',{
     group_id: id
     },function(data){
     
     });*/

}

function showValue(newValue)
{
    document.getElementById("range").innerHTML = newValue + " km";
}

function addFriend(pk) {
    $.get("addFriend", {
        friendId: pk
    }, function (data) {
        var element = document.getElementById("friendButton");
        element.parentNode.removeChild(element);
    });
}