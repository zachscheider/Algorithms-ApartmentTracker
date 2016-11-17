import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.HashMap;

public class PriorityQueue{

    private int maxN;        // maximum number of elements on PQ
    private int n = 0;           // number of elements on PQ
    public Apartment[] costPQ;
    public Apartment[] sqFtPQ;
    public HashMap<String, Integer> costIndex;
	private HashMap<String, Integer> sqFtIndex;

    public PriorityQueue(int theSize){

		if (theSize <= 0) throw new IllegalArgumentException("Initial size must be positive");
        maxN = theSize;

		costPQ = new Apartment[theSize];
		sqFtPQ = new Apartment[theSize];
		costIndex = new HashMap<String, Integer>(theSize);
		sqFtIndex = new HashMap<String, Integer>(theSize);

	}

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    public void insert(Apartment apt){

        costPQ[n] = apt;
        costIndex.put(apt.getIdentifier(), n);

        sqFtPQ[n] = apt;
        sqFtIndex.put(apt.getIdentifier(), n);

        swimCost(n);
        swimSqFt(n++);

        if(n == maxN){
            maxN = 2 * costPQ.length;
            costPQ = Arrays.copyOf(costPQ, maxN);
            sqFtPQ = Arrays.copyOf(sqFtPQ, maxN);
        }

    }

	public void remove(String identifier){

		if(costIndex.containsKey(identifier)) {

            int costPos, sqFtPos;

			costPos = costIndex.get(identifier);
			exchange(costPos, --n , costPQ, costIndex);
			costPQ[n] = null;
			costIndex.remove(identifier);
			swimCost(costPos);
			sinkCost(costPos);

			sqFtPos = sqFtIndex.get(identifier);
			exchange(sqFtPos, n, sqFtPQ, sqFtIndex);
			sqFtPQ[n] = null;
			sqFtIndex.remove(identifier);
			swimSqFt(sqFtPos);
			sinkSqFt(sqFtPos);

		}
	}

    public Apartment findApartment(String identifier){
        if(!costIndex.containsKey(identifier)) return null;
        int index = costIndex.get(identifier);
        if(index > -1){
            return costPQ[index];
        }
	    return null;
	}

    public void update(String identifier, int newCost){
        if(!costIndex.containsKey(identifier)) return;
		int index = costIndex.get(identifier);
		if(index > -1){
			costPQ[index].setCostPerMonth(newCost);
			swimCost(index);
			sinkCost(index);
		}
	}

    private void exchange(int pos1, int pos2, Apartment[] apts, HashMap<String, Integer> index){

		Apartment temp = apts[pos1];
		apts[pos1] = apts[pos2];
		apts[pos2] = temp;
		index.replace(apts[pos1].getIdentifier(), pos1);
		index.replace(apts[pos2].getIdentifier(), pos2);

	}

	private void swimCost(int index){

		while(index > 0 && lowerCost(index, (index - 1)/2)){
			exchange(index, (index - 1)/2, costPQ, costIndex);
			index = (index - 1)/2;
		}
        
	}

    private void sinkCost(int index){

		while(2 * index + 1 < n){			
			int child = 2 * index + 1;
			if (child < n && lowerCost(child + 1, child)) child++;
			if (!lowerCost(child, index)) break;
			exchange(index, child, costPQ, costIndex);
			index = child;
		}
	}

    private boolean lowerCost(int pos1, int pos2){

		if(costPQ[pos1] == null || costPQ[pos2] == null) return false;
		return costPQ[pos1].getCostPerMonth() < costPQ[pos2].getCostPerMonth();
	}

    private void swimSqFt(int index){

		while(index > 0 && lesserSqFt(index, (index - 1)/2)){
			exchange(index, (index - 1)/2, sqFtPQ, sqFtIndex);
			index = (index - 1)/2;
		}
	}

    private void sinkSqFt(int index){

		while(2 * index + 1 < n){			
			int child = 2 * index + 1;
			if (child < n && lesserSqFt(child + 1, child)) child++;
			if (!lesserSqFt(child, index)) break;
			exchange(index, child, sqFtPQ, sqFtIndex);
			index = child;
		}
	}

    private boolean lesserSqFt(int pos1, int pos2){

		if(sqFtPQ[pos1] == null || sqFtPQ[pos2] == null) return false;
		return sqFtPQ[pos1].getSqFootage() > sqFtPQ[pos2].getSqFootage();
	}

    public Apartment minCost(){
        return costPQ[0];
    }

    public Apartment maxSqFt(){
        return sqFtPQ[0];
    }

    public Apartment cityCost(int parent, String city){

		if(parent >= n) return null;
		if(costPQ[parent] == null) return null;
		else if(city.equals(costPQ[parent].getCity().toLowerCase())) return costPQ[parent];
		else {

			Apartment left = cityCost(2 * parent + 1, city);
			Apartment right = cityCost(2 * parent + 2, city);

			if (left == null && right == null) return null;
			else if (right == null) return left;
			else if (left == null) return right;
			else{
				if(left.getCostPerMonth() < right.getCostPerMonth()) return left;
				else return right;
			}
		}
	}

    public Apartment citySqFt(int parent, String city){

		if(parent >= n) return null;
		if(sqFtPQ[parent] == null) return null;
		else if(city.equals(sqFtPQ[parent].getCity().toLowerCase())) return sqFtPQ[parent];
		else {

			Apartment left = citySqFt(2 * parent + 1, city);
			Apartment right = citySqFt(2 * parent + 2, city);

			if (left == null && right == null) return null;
			else if (right == null) return left;
			else if (left == null) return right;
			else{
				if(left.getSqFootage() > right.getSqFootage()) return left;
				else return right;
			}
		}
	}
}