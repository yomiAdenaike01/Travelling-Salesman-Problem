
package Defaults;
//Compare edge weights - used to sort an ArrayList of edges
public class CompareEdge implements java.util.Comparator 
{
	public int compare(Object a, Object b) 
	{
		if (((Edge)a).w < ((Edge)b).w) return(-1);
		if (((Edge)a).w > ((Edge)b).w) return(1);
		return(0);
	}
}
