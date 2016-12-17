function matching(pUser){
    chrome.tabs.executeScript({
        code:"document.querySelector('body').innerText"
    },function(result){
        //위의 code가 실행된 후에 이 함수를 호출해주세요. 그 때 result에 담아주세요.
        var bodyText = result[0];
        var bodyNum = bodyText.split(' ').length; 
        var myNum = bodyText.match(new RegExp('\\b('+pUser+')\\b', 'gi')).length;
        var per = (myNum / bodyNum * 100).toFixed(1);

        document.getElementById('result').innerHTML = (myNum+'/'+bodyNum+ '=('+(per)+'%)');
    });    
}

//크롬 스토리지에 저장된 값을 가져오세요.
chrome.storage.sync.get(function(data){
    //#user의 값으로 data의 값을 입력해주세요
    document.querySelector('#search').value = data.userWords;
    matching(data.userWords);

})

//컨텐츠 페이지의 #user 입력된 값이 변경 되었을 '때' 컨텐츠 페이지에 몇개의 단어가 등장하는지 계산해주세요.
document.querySelector('#search').addEventListener('change', function(){
    var user = document.querySelector('#search').value;

    //크롬 스토리지에 입력값을 저장한다.
    chrome.storage.sync.set({
        userWords:user 
    });
    
    matching(user);
})
