/*
function obj$(id) 根据id得到对象 
function val$(id) 根据id得到对象的值 
function trim(str) 删除左边和右边空格 
function ltrim(str) 删除左边空格 
function rtrim (str) 删除右边空格 
function isEmpty(str) 字串是否有值 
function equals(str1, str2) js判断比较两字符串是否相等 
function equalsIgnoreCase(str1, str2) js判断忽略大小写比较两个字符串是否相等 
function isChinese(str) js判断判断是否中文 
function isEmail(strEmail) js判断是否电子邮件 
function isImg(str) js判断是否是一个图片格式的文件jpg|jpeg|swf|gif 
function isInteger(str) js判断是否是一个整数 
function isFloat js判断是否是一个浮点数 
function isPost(str) js判断是否邮编(1位至6位 
function isMobile(str) js判断是否是手机号 
function isPhone(str) js判断是否是电话号码必须包含区号,可以含有分机号 
function isQQ(str) js判断是否合法的QQ号码 
function isIP(str) js判断是否是合法的IP 
function isDate(str) js判断是否日期类型(例:2005-12-12) 
function isIdCardNo(idNumber) js判断是否是合法的身份证号 
*/ 

function obj$(id) 
{ 
return document.getElementById(id); 
} 


function val$(id) 
{ 
var obj = document.getElementById(id); 
if(obj !== null) 
{ 
return obj.value; 
} 
return null; 
} 


function trim(str) 
{ 
return str.replace(/(^\s*)|(\s*$)/g, ''); 
} 


function ltrim(str) 
{ 
return str.replace(/^\s*/g,''); 
} 


function rtrim(str) 
{ 
return str.replace(/\s*$/,''); 
} 


function isEmpty(str) 
{ 
if(str != null && str.length > 0) 
{ 
return true; 
} 
return false; 
} 


function equals(str1, str2) 
{ 
if(str1 == str2) 
{ 
return true; 
} 
return false; 
} 


function equalsIgnoreCase(str1, str2) 
{ 
if(str1.toUpperCase() == str2.toUpperCase()) 
{ 
return true; 
} 
return false; 
} 


function isChinese(str) 
{ 
var str = str.replace(/(^\s*)|(\s*$)/g,''); 
if (!(/^[\u4E00-\uFA29]*$/.test(str) 
&& (!/^[\uE7C7-\uE7F3]*$/.test(str)))) 
{ 
return false; 
} 
return true; 
} 


function isEmail(str) 
{ 
if(/^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test(str)) 
{ 
return true 
} 
return false; 
} 


function isImg(str) 
{ 
var objReg = new RegExp("[.]+(jpg|jpeg|swf|gif)$", "gi"); 
if(objReg.test(str)) 
{ 
return true; 
} 
return false; 
} 


function isInteger(str) 
{ 
if(/^-?\d+$/.test(str)) 
{ 
return true; 
} 
return false; 
} 


function isFloat(str) 
{ 
if(/^(-?\d+)(\.\d+)?$/.test(str) )
{ 
return true; 
} 
return false; 
} 


function isPost(str) 
{ 
if(/^\d{1,6}$/.test(str)) 
{ 
return true; 
} 
return false; 
} 


function isMobile(str) 
{ 
if(/^1[35]\d{9}/.test(str)) 
{ 
return true; 
} 
return false; 
} 


function isPhone(str) 
{ 
if(/^(0[1-9]\d{1,2}-)\d{7,8}(-\d{1,8})?/.test(str)) 
{ 
return true; 
} 
return false; 
} 


function isQQ(str){ 
if(/^\d{5,9}$/.test(str)) 
{ 
return true; 
} 
return false; 
} 


function isIP(str){ 
var reg = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/; 
if(.test(str)) )
{ 
return true; 
} 
return false; 
} 


function isDate(str) 
{ 
var reg = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/; 
if(reg.test(str)) 
{ 
return true; 
} 
return false; 
} 


function isIdCardNo(idNumber) 
{ 
var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1); 
var varArray = new Array(); 
var lngProduct = 0; 
var intCheckDigit; 

if ((idNumber.length != 15) && (idNumber.length != 18)) 
{ 
return false; 
} 
for(i=0;i<idNumber.length;i++) 
{ 
varArray[i] = idNumber.charAt(i); 
if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) 
{ 
return false; 
} 
else if (i < 17) 
{ 
varArray[i] = varArray[i]*factorArr[i]; 
} 
} 
if (idNumber.length == 18) 
{ 
var date8 = idNumber.substring(6,14); 
if (checkDate(date8) == false) 
{ 
return false; 
} 
for(i=0;i<17;i++) 
{ 
lngProduct = lngProduct + varArray[i]; 
} 
intCheckDigit = 12 - lngProduct % 11; 
switch (intCheckDigit) 
{ 
case 10: 
intCheckDigit = 'X'; 
break; 
case 11: 
intCheckDigit = 0; 
break; 
case 12: 
intCheckDigit = 1; 
break; 
} 
if (varArray[17].toUpperCase() != intCheckDigit) 
{ 
return false; 
} 
} 
else 
{ 
var date6 = idNumber.substring(6,12); 
if (checkDate(date6) == false) 
{ 
return false; 
} 
} 
return true; 
} 