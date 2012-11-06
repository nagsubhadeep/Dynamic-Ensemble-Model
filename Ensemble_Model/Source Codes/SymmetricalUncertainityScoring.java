import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.featureselection.scoring.SymmetricalUncertainty;
import net.sf.javaml.tools.data.FileHandler;

/**
 *The Third Feature Selection is performed using Symmetric Uncertainty Algorithm.
 * Symmetric Uncertainty scores the features based on the algorithm,
 * and finally the best feature is selected. Higher the score, the better is the attribute.
 * 
 * @Author: Deep
 * 
 * */

public class SymmetricalUncertainityScoring
{
	public static void main(String[] args) throws IOException
	{	
		try
		{
			/* Load the purchase data set */
			
			Dataset dataset = FileHandler.loadDataset(new File("P1-CustomerPurchaseData.csv"), 3, ",");
			FileWriter fstream = new FileWriter("Feature_Scoring_output.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			
			//The Symmetric Uncertainty Algorithm is implemented here
			SymmetricalUncertainty su = new SymmetricalUncertainty();
			
			//Algorithm is applied on the dataset here
			su.build(dataset);
			
			for (int i = 0; i<su.noAttributes(); i++)
			{
				System.out.println("The Feature score of the "+(i)+"th attribute is "+su.score(i));
	        	out.write("The Feature score of the "+(i)+"th attribute is "+su.score(i));
			}
	        
	        out.close();
	        
	        FileHandler.exportDataset(dataset,new File("Feature_scoring_output_dataset.txt"));
		}
		
		catch (Exception e)
        {
        	System.err.println("Error: " + e.getMessage());
        }

	}

}
