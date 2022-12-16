jQuery.extend({
	clock: function clock(target) {
		var dayOfTheWeek = new Array("日", "月", "火", "水", "木", "金", "土");
		var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		var day = now.getDay();
		var hour = now.getHours();
		var min = now.getMinutes();
		var sec = now.getSeconds();
		if (month < 10) {
			month = "0" + month;
		}
		if (date < 10) {
			date = "0" + date;
		}
		if (hour < 10) {
			hour = "0" + hour;
		}
		if (min < 10) {
			min = "0" + min;
		}
		if (sec < 10) {
			sec = "0" + sec;
		}
		var time_str = "現在日時は「" + year + "年" + month + "月" + date + "日" + "（" + dayOfTheWeek[day] + "曜日）" + hour + "時" + min + "分" + sec + "秒」です。";

		// htmlの内容を更新
		target.text(time_str);
		//target.html(time_str);

		// 1000ミリ秒（1秒）毎に更新
		setTimeout(function() {
			clock(target)
		}, 1000);
	}
});

// 現在日時を表示します。
jQuery.clock(jQuery("#degitalClockSample1"));


$(document).ready(function() {
	$("#logout").click(function() {
		return confirm("ログアウトしますか？");
	});



});