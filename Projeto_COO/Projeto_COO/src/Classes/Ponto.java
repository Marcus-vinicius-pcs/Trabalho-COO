package Classes;

public  class Ponto {

	//criei nova classe (PONTO)
	
	private double X;	// coordenada x
	private double Y;	// coordenada y
	private double velocidadeX;
	private double velocidadeY;

	public Ponto(double x, double y, double vX, double vY){
		this.X = x;
		this.Y = y;
		this.velocidadeX = vX;
		this.velocidadeY = vY;
	}

	public double getX(){
		return X;
	}

	public double getY(){
		return Y;
	}

	public void setX(double x){
		this.X = x;
	}

	public void setY(double y){
		this.Y = y;
	}

	public double getVX(){
		return this.velocidadeX;
	}
	public double getVY(){
		return this.velocidadeY;
	}

	public void setVX(double vx){
		this.velocidadeX = vx;
	}

	
	public void setVY(double vy){
		this.velocidadeY = vy;
	}


	public void moverParaCima(double y, long delta){
		this.Y -= y*delta*this.velocidadeY;
	}

	public void moverParaBaixo(double y, long delta){
		this.Y += y*delta*this.velocidadeY;
	}
	public void moverParaEsquerda(double x, long delta){
		this.X -= x*delta*this.velocidadeX;
	}
	public void moverParaDireita(double x, long delta){
		this.X += x*delta*this.velocidadeX;
	}

}

