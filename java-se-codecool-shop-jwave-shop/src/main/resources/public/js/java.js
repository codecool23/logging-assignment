/**
 * Created by gabor on 2017.04.27..
 */

// $(document).ready(function() {
//     var titleList = [];
//     $(".btn-success").on("click", function() {
//         /*var title = $(this).closest(".caption").find("h4").text();*/
//         var button = $('<input type="button" value="new button"/>');
//         var element = $.inArray(title, titleList);
//         if(element === -1) {
//             titleList.push(button);
//             $(".modal-body").prepend("<div>" + button + "</div>");
//         }
//
//         console.log(titleList);
//
//         console.log(button);
//     });
// });

$(document).ready(function() {
    $(".value").on("click", function(){
        var counter = $(this).closest(".modal-body").find(".quantity").val();
        var operator = this.getAttribute("data-product_id");
        if(operator==="add"){counter++;}
        if(operator==="remove"){counter--;}
        $(this).closest(".modal-body").find(".quantity").val(counter);
    });

    // $(".qty").on("click", function () {
    //     // var $qty=$(this).closest('button').find('.qty');
    //     //var currentVal = parseInt($(this).val());
    //     counter++;
    //     $(this).closest(".modal-body").find(".quantity").val(counter);
    //
    //     // var total = 0;
    //     // $(".qty").each(function() {
    //     //     total += parseInt($(".qty").val());
    //     // });
    //     // $(".sum").val(total);
    //     // });
    //
    //
    // });
    //
    // $(".decrease").on("click", function () {
    //     // var $qty=$(this).closest('button').find('.qty');
    //     //var currentVal = parseInt($(this).val());
    //     counter--;
    //     $(this).closest(".modal-body").find(".quantity").val(counter);
    //     // $( "decrease" ).each(function() {
    //     //     parseInt()
    // });
});



















        // $.ajax({
        //     url: "/update?product_id="+productId+"&quantity="+currentVal,
        //     type: "POST",
        //     //data: {"data-qty": qty.val()},
        //     //dataType: "html",
        //     success: function () {
        //     // }
        // });
        //


