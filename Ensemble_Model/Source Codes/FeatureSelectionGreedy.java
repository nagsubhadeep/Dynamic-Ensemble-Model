import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.distance.PearsonCorrelationCoefficient;
import net.sf.javaml.featureselection.subset.GreedyForwardSelection;
import net.sf.javaml.tools.data.FileHandler;

/**
 * Create a feature subset selection algorithm using Greedy Forward Selection
 * The Second Feature Selection is performed by Greedy Forward Selection Method
 * on the features selected by the First Feature Selection.
 * The Greedy Forward Selection selects 5 best subset features from the 10 features
 * selected by the SVM-RFE.
 * 
 * @author Deep
 * 
 */
public class FeatureSelectionGreedy
{

    public static void main(String[] args) throws Exception
    {
        try
        {
        	/* Load the purchase data set */
	        Dataset data = FileHandler.loadDataset(new File("P1-CustomerPurchaseData.csv"), 9, ",");
	        FileWriter fstream = new FileWriter("Subset_Selection_output.txt");
	        BufferedWriter out = new BufferedWriter(fstream);
	    	
	        /*
	         * Construct a greedy forward subset selector that will use the Pearson
	         * correlation to determine the relation between each attribute and the
	         * class label. The first parameter indicates that only one, i.e. 'the
	         * best' attribute will be selected.
	         */	        
	        
	        GreedyForwardSelection gf = new GreedyForwardSelection(5, new PearsonCorrelationCoefficient());
	        
	        /* Apply the algorithm to the data set */
	        gf.build(data);
	        
	        /* Print out the attribute that has been selected */
	        System.out.println("The Selected Attributes are "+gf.selectedAttributes());
	        out.write("The Selected Attributes are "+gf.selectedAttributes());
	        out.close();
	        
	        FileHandler.exportDataset(data,new File("Subset_Selection_output_dataset.txt"));
        }
        
        catch (Exception e)
        {
        	System.err.println("Error: " + e.getMessage());
        }
        
    }
}