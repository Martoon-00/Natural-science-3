class SolutionLoader{
	private static var lastCall = new Date(0)
	private static var minDelay = 10
	
	private static var sentMessageId = 0
	private static var recMessageId = -1
	
	private var url:String
	
	function SolutionLoader(port:Number, method:String, init:Point, n:Number, step:Number, r:Number, sigma:Number, b:Number){
		url = "http://localhost:" 
			+ port 
			+ "/solve/"
			+ method 
			+ "?r="
			+ r 
			+ "&sigma="
			+ sigma 
			+ "&b="
			+ b 
			+ "&x0="
			+ init.x
			+ "&y0="
			+ init.y 
			+ "&z0="
			+ init.z
			+ "&step="
			+ step 
			+"&n="
			+ n
			+ "&id="
			+ (sentMessageId++);
	}
	
	function load(f:Function):Boolean {
		if (new Date() - lastCall < minDelay) return
		lastCall = new Date()
		
		var lv = new LoadVars()
		var points = new Array()
		lv.decode = function(s:String){
			var a = s.split("\n")
			var id = a[0] / 1
			if (id <= recMessageId) return;
			recMessageId = id
			for (var i = 1; i < a.length - 1; i++){
				var b = a[i].slice(1, a[i].length - 1).split("; ");
				for (var j = 0; j < 3; j++){
					var d = b[j]
					var k = d.indexOf(",")
					if (k != -1) b[j] = d.slice(0, k) + "." + d.slice(k + 1)
				}
				points.push(new Point(b[0], b[1], b[2]))
			}
		}
		lv.onLoad = function(){
			f(points)
		}
		return lv.load(url)
	}
}