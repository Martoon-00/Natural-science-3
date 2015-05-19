class Point {
	public var x:Number;
	public var y:Number;
	public var z:Number;
	
	function Point(x:Number, y:Number, z:Number){
		this.x = x
		this.y = y 
		this.z = z
		
		watch("x", function(){ return arguments[1] })
		watch("y", function(){ return arguments[1] })
		watch("z", function(){ return arguments[1] })
	}
	
	function transform(t:Transformation){
		return t.apply(this)
	}
	
	function toString():String {
		return "(" + x + ", " + y + ", " + z + ")"
	}
}