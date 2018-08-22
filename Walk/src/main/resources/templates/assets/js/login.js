function log() {
    $(".login").css("display","block");
    $("#max").css("display","block");
    unScroll();
    //$("body").css("overflow-y","hidden");
}
function clo() {
    $(".login").css("display","none");
    $("#max").css("display","none");
    removeUnScroll();
}
function unScroll() {
    var top = $(document).scrollTop();
    $(document).on('scroll.unable',function (e) {
        $(document).scrollTop(top);
    })
}
function removeUnScroll() {
    $(document).unbind("scroll.unable");
}