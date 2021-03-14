public class Gauss
{
	private double[][] p;

	public Gauss(double[][] pole)
	{
		this.p = pole.clone();
		for(int i = 0; i < pole.length; i++)
			this.p[i] = pole[i].clone();
	}

	@Override
	public String toString()
	{
		StringBuffer res = new StringBuffer("{\n");
		for(int i = 0; i < p.length; i++) {
			res.append("\t{");
			for(int j = 0; j < p[i].length; j++)
			{
				res.append(p[i][j] + ((j+1 <p[i].length)?", ":""));
			}
			res.append((i+1 < p.length)?"},\n":"} \n}\n"); 
		}
		return res.toString(); 
	}

	public double[] dajRiadok(int r) { return p[r]; }

	public void vydel(int riadok, double cim)
	{
		for (int i = 0; i < p[riadok].length; i++)
		{
			p[riadok][i] /= cim;
		}
	}

	public void vymen(int riadok1, int riadok2)
	{
		double temp[] = p[riadok1];

		p[riadok1] = p[riadok2];
		p[riadok2] = temp;
	}

	public void pripocitaj(int riadok1, double x, int riadok2)
	{
		for (int i = 0; i < p[riadok1].length; i++)
		{
			p[riadok1][i] += x * p[riadok2][i];
		}
	}

	public int hladaj(int r)
	{
		for (int i = r + 1; i < p.length; i++)
		{
			if (p[i][r] != 0) return i;
		}
		return -1;
	}

	public boolean gauss()
	{
		int j;
		for (int r = 0; r < p.length; r++)
		{
			if (p[r][r] == 0)
			{
				j = hladaj(r);
				if (j == -1) return false;
				vymen(r, j);
			}
			vydel(r, p[r][r]);
			for (int k = 0; k < p.length; k++)
			{
				if (k != r)
				{
					pripocitaj(k, -p[k][r], r);
				}
			}
		}
		return true;
	}

	public static void main(String[] args)
	{
		Gauss p = new Gauss(new double[][]{
			{2, 4, -2, 6},
			{0, -3, 6, -3},
			{4, 1, -2, 2} });
		System.out.println(p.gauss());
		System.out.println(p);
	}
}
