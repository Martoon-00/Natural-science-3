class Transformation {
	private var a:Array
	
	function Transformation(a:Array){
		this.a = a
	}
	
	function compose(t:Transformation){   
		var b = new Array(new Array(0, 0, 0), new Array(0, 0, 0), new Array(0, 0, 0))
		for (var i = 0; i < 3; i++)
			for (var j = 0; j < 3; j++)
				for (var k = 0; k < 3; k++)
					b[i][j] += t.a[i][k] * a[k][j]
		return new Transformation(b)
	}
	
	function apply(p:Point){
		return new Point(a[0][0] * p.x + a[0][1] * p.y + a[0][2] * p.z,
						 a[1][0] * p.x + a[1][1] * p.y + a[1][2] * p.z,
						 a[2][0] * p.x + a[2][1] * p.y + a[2][2] * p.z)
	}
	
	function toString():String {
		return a[0] + "\n" + a[1] + "\n" + a[2] + "\n"
	}
	
	
	static function flatRotation(rot:Number){
		rot *= Math.PI / 180
		return new Transformation(new Array(
								new Array(Math.cos(rot), -Math.sin(rot), 0),
								new Array(Math.sin(rot), Math.cos(rot), 0),
								new Array(0, 0, 1)
							))
	}
	
	static function vertRotation1(rot:Number){
		rot *= Math.PI / 180
		return new Transformation(new Array(
								new Array(1, 0, 0),
								new Array(0, Math.cos(rot), -Math.sin(rot)),
								new Array(0, Math.sin(rot), Math.cos(rot))
							))
	}
	
	static function vertRotation2(rot:Number){
		rot *= Math.PI / 180
		return new Transformation(new Array(
								new Array(Math.cos(rot), 0, -Math.sin(rot)),
								new Array(0, 1, 0),
								new Array(Math.sin(rot), 0, Math.cos(rot))
							))
	}
	
	static function makeFlat(seeAngle:Number){
		return new Transformation(new Array(
								 new Array(1, 0, Math.cos(seeAngle)),
								 new Array(0, 1, Math.sin(seeAngle)),
								 new Array(0, 0, 0) 
							   ))
	}
	
	
	static function makeScale(scale:Number){
		return new Transformation(new Array(
								 new Array(scale, 0, 0),
								 new Array(0, scale, 0),
								 new Array(0, 0, scale) 
							   ))
	}
	
	
}