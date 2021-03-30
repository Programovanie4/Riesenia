interface HeapStringInterface  {
	// reprezentujte Max-heap haldu, koren haldy je max

  public String first();		// vrati najvacsi prvok z haldy
  public String remove();		// odstrani najvacsi prvok z haldy
  public void insert(String str);// prida prvok str do haldy, halda zostane haldou
  public int size();			// vrati velkost haldy, pocet prvkov
}
  