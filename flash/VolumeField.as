class VolumeField extends MovieClip {
	private var getFlatRotation:Function // rotation about Oz
	private var getVertRotation:Function // rotation about Ox or Oy
	private var getScale:Function
	private var lines = new Array()
	private var seeAngle = 60
	
	private var curTransformation:Transformation
	private var makeFlatTransform:Transformation
	
	private var deleteLine = new Array()
	
	function VolumeField(){
		makeFlatTransform = new Transformation(new Array(
												 new Array(0.8, 0.6, 0),
												 new Array(-0.2, 0.4, -1),
												 new Array(0, 0, 0) 
											   ))
		//setInterval(function(o){ o.enterFrame.apply(o) }, 100, this)
		
	}
	
	
	function update(){
		clear()
		curTransformation = Transformation.vertRotation2(getVertRotation())
			.compose(Transformation.flatRotation(getFlatRotation()))
			.compose(Transformation.makeScale(getScale()))
			.compose(makeFlatTransform)
		for (var i in lines){
			var line = lines[i]
			var p1 = transformPoint(line.p1)
			var p2 = transformPoint(line.p2)
			lineStyle(2, line.color, line.alpha, false, "none")
			moveTo(p1.x, p1.y)
			lineTo(p2.x, p2.y)
		}
		for (var i in deleteLine){
			deleteLine[i]()
			delete deleteLine[i]
		}
	}
	
	function addLine(l:Line):Function {
		var size = lines.push(l)
		var lines_ = lines
		return function(){ delete lines_[size - 1] }
	}
	
	function addTempLine(l:Line) {
		deleteLine.push(addLine(l))
	}
	
	
	function setFlatRotation(f:Function){ getFlatRotation = f }
	function setVertRotation(f:Function){ getVertRotation = f }
	function setScale(f:Function){ getScale = f }
	
	function transformPoint(p:Point){
		return p.transform(curTransformation)
	}
}