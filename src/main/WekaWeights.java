package main;
import weka.classifiers.Classifier;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.trees.M5P;
import weka.classifiers.trees.RandomForest;

public class WekaWeights
{
	public static void main(String args[]) throws Exception
	{
		if (args.length != 1)
		{
			System.err.println("give model file as param");
			System.exit(1);
		}
		Classifier cls = (Classifier) weka.core.SerializationHelper.read(args[0]);
		System.err.println("Reading " + args[0] + " : " + cls.getClass().getName());
		String attributes[] = null;
		double weights[] = null;
		if (cls instanceof RandomForest)
		{
			weights = ((RandomForest) cls).getAttributeWeights();
			attributes = ((RandomForest) cls).getAttributes();
		}
		else if (cls instanceof SMOreg)
		{
			weights = ((SMOreg) cls).getAttributeWeights();
			attributes = ((SMOreg) cls).getAttributes();
		}
		else if (cls instanceof SMO)
		{
			weights = ((SMO) cls).getAttributeWeights();
			attributes = ((SMO) cls).getAttributes();
		}
		else if (cls instanceof M5P)
		{
			weights = ((M5P) cls).getAttributeWeights();
			attributes = ((M5P) cls).getAttributes();
		}
		else
			throw new Error("unknown classifier");
		for (int i = 0; i < weights.length; i++)
			System.out.println(attributes[i] + " " + weights[i]);
	}
}
