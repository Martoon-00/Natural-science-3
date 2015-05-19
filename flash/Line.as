class Line {
	public var p1:Point, p2:Point
	public var color:Number
	public var alpha:Number
	
	function Line(p1:Point, p2:Point, color:Number, alpha:Number){
		this.p1 = p1
		this.p2 = p2
		this.color = color
		this.alpha = alpha == undefined ? 100 : alpha
		
		watch("p1", function(){ return arguments[1] })
		watch("p2", function(){ return arguments[1] })
		watch("color", function(){ return arguments[1] })
		watch("alpha", function(){ return arguments[1] })
	}
	
	function toString():String {
		return p1 + " - " + p2
	}
}