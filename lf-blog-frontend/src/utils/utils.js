export const isNotNullORBlank = (...args)=> {
  for (var i = 0; i < args.length; i++) {
    var argument = args[i];
    if (argument == null || argument == '' || argument == undefined) {
      return false;
    }
  }
  return true;
}
export function getUrlKey(name,url){           //获取当前url            //获取#/之前的字符串
  var cs = url.split('?')[1];                //获取?之后的参数字符串
  var cs_arr = cs.split('&'); 
  var cs={};
  for(var i=0;i<cs_arr.length;i++){         //遍历数组，拿到json对象
    cs[cs_arr[i].split('=')[0]] = cs_arr[i].split('=')[1]
  }
  return cs;
}
