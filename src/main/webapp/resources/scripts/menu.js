//----------DHTML Menu Created using AllWebMenus PRO ver 5.3-#908---------------
//F:\Orgify_files\orgify_menu.awm
var awmMenuName='menu';
var awmLibraryBuild=908;
var awmLibraryPath='/awmdata';
var awmImagesPath='/awmdata/menu';
var awmSupported=(navigator.appName + navigator.appVersion.substring(0,1)=="Netscape5" || document.all || document.layers || navigator.userAgent.indexOf('Opera')>-1 || navigator.userAgent.indexOf('Konqueror')>-1)?1:0;
if (awmSupported){
var nua=navigator.userAgent,scriptNo=(nua.indexOf('Chrome')>-1)?2:((nua.indexOf('Safari')>-1)?2:(nua.indexOf('Gecko')>-1)?2:((nua.indexOf('Opera')>-1)?4:1));
var mpi=document.location,xt="";
var mpa=mpi.protocol+"//"+mpi.host;
var mpi=mpi.protocol+"//"+mpi.host+mpi.pathname;
//alert("Mpi pathname " + mpi.pathname);
if(scriptNo==1){oBC=document.all.tags("BASE");if(oBC && oBC.length) if(oBC[0].href) mpi=oBC[0].href;}
while (mpi.search(/\\/)>-1) mpi=mpi.replace("\\","/");
mpi=mpi.substring(0,mpi.lastIndexOf("/")+1);
var mpin=mpi;
var e=document.getElementsByTagName("SCRIPT");
for (var i=0;i<e.length;i++){if (e[i].src){if (e[i].src.indexOf(awmMenuName+".js")!=-1){xt=e[i].src.split("/");if (xt[xt.length-1]==awmMenuName+".js"){xt=e[i].src.substring(0,e[i].src.length-awmMenuName.length-3);if (e[i].src.indexOf("://")!=-1){mpi=xt;}else{if(xt.substring(0,1)=="/")mpi=mpa+xt; else mpi+=xt;}}}}}
while (mpi.search(/\/\.\//)>-1) {mpi=mpi.replace("/./","/");}
var awmMenuPath=mpi.substring(0,mpi.length-1);
while (awmMenuPath.search("'")>-1) {awmMenuPath=awmMenuPath.replace("'","%27");}
document.write("<SCRIPT SRC='"+(awmMenuPath+awmLibraryPath).replace(/\/$/,"")+"/awmlib"+scriptNo+".js'><\/SCRIPT>");
var n=null;
awmzindex=1000;
}

var awmImageName='';
var awmPosID='';
var awmPosClass='';
var awmSubmenusFrame='';
var awmSubmenusFrameOffset;
var awmOptimize=0;
var awmHash='TCUFXCFDJSCYPULQMMFIWETAVWTY';
var awmNoMenuPrint=1;
var awmUseTrs=0;
var awmSepr=["0","","",""];
var awmMarg=[0,0,0,0];
function awmBuildMenu(){
if (awmSupported){
awmImagesColl=["v5_bullets_75.gif",10,10,"v5_bullets_76.gif",10,10,"v5_bullets_76b.gif",11,10];
awmCreateCSS(0,1,0,n,n,n,n,n,'solid','0','#000066','0px 0px 0px 0',0,'0px / 0px',n);
awmCreateCSS(1,2,0,'#FFFFFF','#FF6100',n,'16px Arial, Helvetica, sans-serif',n,'solid','1','#640000','12px 10px 12px 10',0,'0px / 0px',n);
awmCreateCSS(0,2,0,'#FFFFFF','#FF6100',n,'bold 16px Arial, Helvetica, sans-serif',n,'solid','1','#640000','12px 10px 12px 10',0,'0px / 0px',n);
awmCreateCSS(1,2,0,'#640000','#F1EFEB',n,'14px Arial, Helvetica, sans-serif',n,'solid','1','#640000','12px 10px 12px 10',0,'0px / 0px',n);
awmCreateCSS(0,2,0,'#640000','#F1EFEB',n,'bold 14px Arial, Helvetica, sans-serif',n,'solid','1','#640000','12px 10px 12px 10',0,'0px / 0px',n);
awmCreateCSS(1,2,0,'#640000','#F1EFEB',n,'12px Arial, Helvetica, sans-serif',n,'solid','1','#DFC0AD','12px 10px 12px 10',0,'0px / 0px',n);
awmCreateCSS(0,2,0,'#640000','#F1EFEB',n,'bold 12px Arial, Helvetica, sans-serif',n,'solid','1','#640000','12px 10px 12px 10',0,'0px / 0px',n);
var s0=awmCreateMenu(0,0,0,0,1,0,0,0,0,10,10,0,0,0,1,1,0,n,n,100,1,0,20,-10,125,-1,1,200,200,0,0,0,"0,0,0",n,n,n,n,n,n,n,n,0,0,0,0,1,0,0,0,1,0,0,2,n,n,'',n,0,[]);
it=s0.addItemWithImages(1,2,2,"Home",n,n,"",n,n,n,3,3,3,n,n,n,"",n,n,n,n,n,0,0,2,n,n,n,n,n,n,0,0,0,0,0,n,n,n,0,0,0,0,n);
it=s0.addItemWithImages(3,4,4,"What is it?",n,n,"",n,n,n,3,3,3,n,n,n,"",n,n,n,n,n,0,0,2,n,n,n,n,n,n,0,0,0,0,0,n,n,n,0,0,0,9,n);
it=s0.addItemWithImages(3,4,4,"How it works",n,n,"",n,n,n,3,3,3,0,1,2,"",n,n,n,n,n,0,0,2,n,n,n,n,n,n,0,0,0,0,0,n,n,n,0,0,0,1,n);
var s1=it.addSubmenu(0,0,0,1,3,0,0,0,1,1,0,n,n,100,0,1,0,0,1,200,200,0,0,"0,0,0",0,"1,0,0,1,0,0,15,0,1,1",1);
it=s1.addItemWithImages(5,6,6,"Quick view",n,n,"",n,n,n,3,3,3,n,n,n,"",n,n,n,n,n,0,0,2,n,n,n,n,n,n,0,0,0,0,0,n,n,n,0,0,0,3,n);
it=s1.addItemWithImages(5,6,6,"Leaders",n,n,"",n,n,n,3,3,3,n,n,n,"",n,n,n,n,n,0,0,2,n,n,n,n,n,n,0,0,0,0,0,n,n,n,0,0,0,4,n);
it=s1.addItemWithImages(5,6,6,"Members",n,n,"",n,n,n,3,3,3,n,n,n,"",n,n,n,n,n,0,0,2,n,n,n,n,n,n,0,0,0,0,0,n,n,n,0,0,0,5,n);
it=s0.addItemWithImages(3,4,4,"Features",n,n,"",n,n,n,3,3,3,n,n,n,"",n,n,n,n,n,0,0,2,n,n,n,n,n,n,0,0,0,0,0,n,n,n,0,0,0,2,n);
it=s0.addItemWithImages(3,4,4,"FAQs",n,n,"",n,n,n,3,3,3,n,n,n,"",n,n,n,n,n,0,0,2,n,n,n,n,n,n,0,0,0,0,0,n,n,n,0,0,0,11,n);
it=s0.addItemWithImages(3,4,4,"Downloads",n,n,"",n,n,n,3,3,3,n,n,n,"",n,n,n,n,n,0,0,2,n,n,n,n,n,n,0,0,0,0,0,n,n,n,0,0,0,10,n);
s0.pm.buildMenu();
}}