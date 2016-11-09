import sheffield.*;
public class BeanMachine {
	
	private static EasyReader read = new EasyReader();
	private static EasyWriter write = new EasyWriter();
	private static int numOfBalls = 
	read.readInt("Please enter the number of balls: ");
	private static  int numOfBuckets = 
	read.readInt("Please enter the number of buckets: ");
	//ballIndex will give the position of each ball as it drops
	private static int ballIndex = 0;
	//bucketArray represents the line of buckets so each element equates to
	//a single bucket
	private static int [] bucketArray = new int [numOfBuckets];
	
	
	private static void GeneratePaths() {
		write.println("Path taken by each ball: ");
		//Print the path taken by each ball. Each line of characters 
		//represents the path of 1 ball.
		for (int y=0; y<numOfBalls; y++) {
			write.println("");
				for (int x=1; x<numOfBuckets; x++) {
				//The direction variable determines a left or right turn at a 
				//50-50 chance. If the ball turns right, index is incremented.
					double direction = Math.random();
					if (direction<=0.5) {
						write.print("L");
					} else {
						write.print("R");
						ballIndex++;
					}
				}
					bucketArray[ballIndex]++;
					ballIndex = 0;	
			}
		}

		
	private static void PrintDistribution() {
	//Nested for loop to print the distribution of the balls.
		for (int i=numOfBalls; i>=1; i--) {
			write.println("");
				for (int j=0; j<numOfBuckets; j++) {
					if (bucketArray[j] < i && i == 1) {
						write.print("_ ");
					} else if (bucketArray[j] < i) {
						write.print("  ");
					} else {
						write.print("* ");
					}
				}
			}
		}

		
	private static void GetStats() {
		//Find the largest number of balls collected by a bucket.
		int modal = 0;
		int maxNumber = bucketArray[0];
		for (int i=1; i<numOfBuckets; i++) {
			if(maxNumber < bucketArray[i]) {
				maxNumber = bucketArray[i];
			}
		}
		write.println("");
		write.println("");
			
		//Search for the index of the largest number found.
		//Written with the help of Dr. Siobhan North's week 5 lecture notes.
		int target = maxNumber;
		while (modal<numOfBuckets && 
			bucketArray[modal] != target)
		modal++;
		write.print("Modal bucket is number ");
		write.println(modal+1); //Add 1 to display modal correctly.
			
			
		//Calculate the mean bucket
		double sumXBucketNum = 0;
		double mean = 0;
			
		for (int i=0; i<numOfBuckets; i++) {
			int sumOfBalls = bucketArray[i];
			int bucketIndex = i+1;
			int temp = (sumOfBalls*bucketIndex);
		//For the first iteration, mean takes on the value of temp, and for
		//the remaining iterations it is incremented by each value of temp.
			if (i == 0) {
				sumXBucketNum = temp;
			} else {
				sumXBucketNum = sumXBucketNum+temp;
			}
		}
		mean = sumXBucketNum/numOfBalls;
		write.println("The mean bucket is "+mean);
	}
	
		
	public static void main(String[] args) {
		GeneratePaths();
		PrintDistribution();
		GetStats();
	}
}