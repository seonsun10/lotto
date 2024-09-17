
$(function() {
    doAction.init();
});

var doAction = function(){
    var self;

    return {
        init: function(){
            self = this;
            self.callLotto();
        },
        /* 로또 호출 */
        callLotto: function(){

            $.ajax({
                url: '/getLottoData',
                type: 'GET',
                data: {drwNo: 1137},
                success: function(data){
                    self.afterCallLotto(data);
                },
                error: function(error){
                    console.log('error == > ' + error);
                }
            })
        },
        /* 로또 데이터 조회 후처리 */
        afterCallLotto(data){
            console.log(data);
            var target = $("#lottoTb tbody");
            var dataHtml = '';
            // 1차 데이터 출력
            dataHtml = '<tr><td>총상금액</td><td>' + data.totSellamnt    + '</td></tr>'
                     + '<tr><td>총당첨금</td><td>' + data.firstAccumamnt + '</td></tr>'
                     + '<tr><td>1등 당첨금</td><td>' + data.firstWinamnt   + '</td></tr>'
                     + '<tr><td>1등 당첨인원</td><td>' + data.firstPrzwnerCo + '</td></tr>'
                     + '<tr>'
                     + '    <td>당첨번호</td>'
                     + '    <td>'
                     +          data.drwtNo1 + ' '
                     +          data.drwtNo2 + ' '
                     +          data.drwtNo3 + ' '
                     +          data.drwtNo4 + ' '
                     +          data.drwtNo5 + ' '
                     +          data.drwtNo6 + ' + 보너스 : '
                     +          data.bnusNo + ' '
                     + '    </td>'
                     + '</tr>'
            ;

            target.append(dataHtml);
        }
    }
}();