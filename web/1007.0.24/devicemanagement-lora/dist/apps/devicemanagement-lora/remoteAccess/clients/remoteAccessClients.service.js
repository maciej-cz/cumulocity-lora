"use strict";function RemoteAccessClientsService(e,t){var n,s;this.STATUSES={CONNECTING:"CONNECTING",CONNECTED:"CONNECTED",DISCONNECTED:"DISCONNECTED",ERROR:"ERROR"},this.getConnectionStatus=function(){return s.status||this.STATUSES.DISCONNECTED},this.setConnectionStatus=function(e){s.status=e,t.setItem(n,JSON.stringify(s))},this.getParams=function(){return s},this.loadCss=function(e){var t=document.createElement("link");t.href=e,t.type="text/css",t.rel="stylesheet",t.media="screen,print",document.getElementsByTagName("head")[0].appendChild(t)},this.loadJs=function(e){var t=document.createElement("script");t.src=e,document.getElementsByTagName("head")[0].appendChild(t)},n=function(e){var t,n={},s=/[?&]?([^=]+)=([^&]*)/g;for(e=e.split("+").join(" ");t=s.exec(e);){var a=t[1],c=t[2];n[decodeURIComponent(a)]=decodeURIComponent(c)}return n}(e.search).paramsId;try{s=JSON.parse(t.getItem(n))}catch(e){console.err("Cannot load connection parameters!")}}