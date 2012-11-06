import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.featureselection.ranking.RecursiveFeatureEliminationSVM;
import net.sf.javaml.tools.data.FileHandler;

/**
 * Shows the basic steps to create use a feature ranking algorithm using SVMRFE.
 * 
 * The first feature selection is performed on the whole dataset using
 * linear Support Vector Machine, which runs in linear time.
 * For a linear SVM, the feature importance can be derived from the weight vector
 * of the hyper-plane, a procedure known as Recursive Feature Elimination
 * SVM (SVM-RFE). The SVM-RFE ranks the features, and the best 10 features
 * are selected from the dataset. Lower the rank, better is the attribute.
 * 
 * @author Deep
 * 
 */
public class FeatureRankingSVMRFE
{
    public static void main(String[] args) throws Exception
    {
        
    	try
    	{
	    	/* Load the purchase data set */
	    	Dataset data = FileHandler.loadDataset(new File("P1-CustomerPurchaseData.csv"), 13, ",");
//	    	FileWriter fstream = new FileWriter("Ensemble_Ranking_output.txt");
			BufferedWriter out = new BufferedWriter(new FileWriter("Ensemble_Ranking_output.txt"));
	    	
	        /* Create a feature ranking algorithm using 
	         * Recursive Feature Elimination Support Vector Machines 
	         * Lower the score, the better the attribute */
			
	    	RecursiveFeatureEliminationSVM svmrfe = new RecursiveFeatureEliminationSVM(10.5);
	    	
	    	//Apply the algorithm on the dataset
	    	svmrfe.build(data);
	    	
	    	//Print the ranks
	    	for (int i = 0; i < svmrfe.noAttributes(); i++)
	    	{
	    		System.out.println("Feature Ranking of "+ (i)+"th attribute is "+svmrfe.rank(i));
	    		out.write("Feature Ranking of "+ (i)+"th attribute is "+svmrfe.rank(i));
	    	}
	    	
	        out.close();
	        
	        FileHandler.exportDataset(data,new File("Ensemble_Ranking_Output_Dataset.txt"));
    	}
    	
    	catch (Exception e)
        {
        	System.err.println("Error: " + e.getMessage());
        }
        
    }

}