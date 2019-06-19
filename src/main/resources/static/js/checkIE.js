var DEFAULT_VERSION = 9.0;
var ua = navigator.userAgent.toLowerCase();
var isIE = ua.indexOf("msie")>-1;
var safariVersion;
if(isIE){
    safariVersion =  ua.match(/msie ([\d.]+)/)[1];
}
//若低于IE9，强制跳转
if(safariVersion <= DEFAULT_VERSION ){
    window.location.href = "/error/unsupport.html"
}