package main;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import weka.classifiers.Classifier;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.trees.M5P;
import weka.classifiers.trees.RandomForest;

public class WekaWeightsTest
{
	public static void main(String args[])
	{
		try
		{
			//			System.out.println(Version.VERSION);
			//			Classifier cls = (Classifier) weka.core.SerializationHelper
			//					.read("/home/martin/opentox-ruby/www/opentox/weka/model/1758.model");

			File models[] = new File("/home/martin/opentox-ruby/www/opentox/weka/model/")
					.listFiles(new FilenameFilter()
					{

						@Override
						public boolean accept(File dir, String name)
						{
							return name.endsWith(".model");
						}
					});
			for (File file : models)
			{
				//				WekaWeights.main(new String[] { file.getAbsolutePath() });
				//				if (true == true)
				//					return;

				Classifier cls = (Classifier) weka.core.SerializationHelper.read(file.getAbsolutePath());
				System.out.println(file);
				System.out.println(cls.getClass().getName());
				System.out.println(cls);
				System.out.println();
				System.out.println(file);
				System.out.println(cls.getClass().getName());
				System.out.println("Weights:");

				if (cls instanceof RandomForest)
				{
					RandomForest rf = (RandomForest) cls;
					System.out.println(Arrays.toString(rf.getAttributeWeights()));

				}
				else if (cls instanceof SMOreg)
				{
					SMOreg smo = (SMOreg) cls;
					System.out.println(Arrays.toString(smo.getAttributeWeights()));
				}
				else if (cls instanceof SMO)
				{
					SMO smo = (SMO) cls;
					System.out.println(Arrays.toString(smo.getAttributeWeights()));
				}
				else if (cls instanceof M5P)
				{
					M5P m5p = (M5P) cls;
					System.out.println(Arrays.toString(m5p.getAttributeWeights()));
				}
				else
				{
					throw new Error("unknown classifier");
				}

				System.out.println();
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
