
function call(nextToken){
    var apiKey = 'AIzaSyAnlJRtoeRovJ8HFYJ2i_Wba89-Pcbocc'
    var playlistId = playlist.value;
    var url = 'https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId='+playlistId+'&key='+apiKey+'8&maxResults=50';

    var req = new XMLHttpRequest();
    var pageToken='';
    if(nextToken){
        pageToken='&pageToken='+nextToken;
    }
    req.open('GET', url+pageToken, true);
    req.onreadystatechange = function (aEvt) {
      if (req.readyState == 4) {
         if(req.status == 200){
              var result = JSON.parse(req.responseText);
              var items = result.items;
              for(var i=0; i<items.length; i++){
                  var vid = items[i].snippet.resourceId.videoId;
                  var vurl = 'https://www.youtube.com/watch?v='+vid;
                  document.getElementById('result').innerHTML += '<a href='+vurl+'>'+items[i].snippet.title+'</a></br>';
              }
              if(result.nextPageToken){
                call(result.nextPageToken);
              }
         }else{
          alert("Error loading page\n");
         }
      }
    };
    req.send(null); 
}


document.getElementById('playlist').addEventListener('change', function(){
    call();
})
