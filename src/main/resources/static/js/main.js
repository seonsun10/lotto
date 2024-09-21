
$(function() {
    doAction.init();
});

var doAction = function() {
    var self;

    return {
        init(){
            self = this;
            self.callLotto(); // 로또 호출
            self.bindEvent(); // 이벤트 처리
        },
        /* 로또 호출 */
        callLotto(){

            $.ajax({
                url: '/getLottoData',
                type: 'GET',
                data: {drwNo: 1136},
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
            var target = $("#lottoTb tbody");
            var dataHtml = '';

            // 1차 데이터 출력
            if(data.resultValue == 'success' || data != null){
                dataHtml = '<tr><td>총상금액</td><td>' + data.totSellamnt    + '</td></tr>'
                         + '<tr><td>총당첨금</td><td>' + data.firstAccumamnt + '</td></tr>'
                         + '<tr><td>1등 당첨금</td><td>' + data.firstWinamnt   + '</td></tr>'
                         + '<tr><td>1등 당첨인원</td><td>' + data.firstPrzwnerCo + '</td></tr>'
                         + '<tr>'
                         + '    <td>당첨번호</td>'
                         + '    <td class="numTd">'
                         +          '<span>' + data.drwtNo1 + '</span> '
                         +          '<span>' + data.drwtNo2 + '</span> '
                         +          '<span>' + data.drwtNo3 + '</span> '
                         +          '<span>' + data.drwtNo4 + '</span> '
                         +          '<span>' + data.drwtNo5 + '</span> '
                         +          '<span>' + data.drwtNo6 + '</span> <img class="plus" src="/img/plus.png"/> '
                         +          '<span>' + data.bnusNo + ' </span>'
                         + '    </td>'
                         + '</tr>'
                ;

                target.append(dataHtml);
            }else{
                target.append("<tr><td colspan='2'>데이터가 없습니다.</td></tr>");
            }

            // 로또 색상 처리
            self.lottoColor();
        },
        /* 이벤트 처리 */
        bindEvent(){

        },
        /* 로또 색상 처리 */
        lottoColor(){
            $("#lottoTb tbody tr td span").each(function(){
                var color = {n1:'#fbc400', n2:'#69c8f2', n3: '#ff7272', n4: '#b2b2b2', n5: '#b0d840'};
                var num = $(this).text();
                if(num < 11) $(this).css('background-color', color.n1);
                else if(num < 21) $(this).css('background-color', color.n2);
                else if(num < 31) $(this).css('background-color', color.n3);
                else if(num < 41) $(this).css('background-color', color.n4);
                else $(this).css('background-color', color.n5);
            });
        }
    }
}();