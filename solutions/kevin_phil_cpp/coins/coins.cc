#include <iostream>
#include <iterator>
#include <vector>
#include <tuple>

std::tuple<int, std::vector<int>> calcMinCoins(const std::vector<int> coins, int target)
{
	std::vector<int> minCoins(target+1, 2*target);
	std::vector<int> pickedCoins(target+1, -1);
	for(auto x : coins)
	{
		if(x <= target)
		{
			minCoins[x] = 1;
			pickedCoins[x] = x;
		}
	}

	//run the dp
	for(int i=1;i<=target;++i)
	{
//		std::cout << "target " << target << std::endl << "min coins: ";
//		for(auto x : minCoins) std::cout << x << ","; 
//		std::cout << std::endl;
//		std::copy(minCoins.begin(), minCoins.end(), std::ostream_iterator<int>(cout, ","));
		int best = minCoins[i];
		int bestCoin = pickedCoins[i];
		for(auto c : coins)
		{
			if(i-c >= 1)
			{
				int newValue = 1+minCoins[i-c];
				if(newValue<best)
				{
					best=newValue;
					bestCoin = c;
				}
//				best = std::min(best, 1+minCoins[i-c]);
			}
		}
		minCoins[i] = best;
		pickedCoins[i] = bestCoin;
	}

//	std::cout << "after loop target " << target << std::endl << "min coins: ";
//	for(auto x : minCoins) std::cout << x << ","; 
//	std::cout << std::endl;
	
	return std::make_tuple(minCoins[target], pickedCoins);
}

int main()
{
	std::vector<int> coins1 = {1,2,5,10,20,50};
	std::vector<int> coins2 = {1,4,15,20,50};

	int answer;
	std::vector<int> picked;

	auto printPicked = [&]()
	{
		for(auto p : picked) std::cout << p << ",";
		std::cout << "|";
		int start = picked.size()-1;
		while(start != picked[start])
		{
			std::cout << picked[start] << ",";
			start = start - picked[start];
		}
		std::cout << start << std::endl;
	};

	std::tie(answer,picked) = calcMinCoins(coins1, 1);
	std::cout << answer << "|";
	printPicked();

	std::cout << std::endl; 
	std::tie(answer,picked) = calcMinCoins(coins1, 3);
	std::cout << answer << ",";
	printPicked();
//	for(auto p : picked) std::cout << p << ",";
	std::cout << std::endl; 
	std::tie(answer,picked) = calcMinCoins(coins1, 5);
	std::cout << answer << ",";
	printPicked();
//	for(auto p : picked) std::cout << p << ",";
	std::cout << std::endl; 
	std::tie(answer,picked) = calcMinCoins(coins1, 9);
	std::cout << answer << ",";
	printPicked();
//	for(auto p : picked) std::cout << p << ",";
	std::cout << std::endl; 
	std::tie(answer,picked) = calcMinCoins(coins1, 11);
	std::cout << answer << ",";
	printPicked();
//	for(auto p : picked) std::cout << p << ",";
	std::cout << std::endl; 
	std::tie(answer,picked) = calcMinCoins(coins1, 23);
	std::cout << answer << ",";
	printPicked();
//	for(auto p : picked) std::cout << p << ",";
	std::cout << std::endl; 
	std::tie(answer,picked) = calcMinCoins(coins1, 49);
	std::cout << answer << ",";
	printPicked();
//	for(auto p : picked) std::cout << p << ",";
	std::cout << std::endl; 
	std::tie(answer,picked) = calcMinCoins(coins1, 87);
	std::cout << answer << ",";
	printPicked();
//	for(auto p : picked) std::cout << p << ",";
	std::cout << std::endl; 

	std::tie(answer,picked) = calcMinCoins(coins2, 3);
	std::cout << answer << ",";
	printPicked();
//	for(auto p : picked) std::cout << p << ",";
	std::cout << std::endl; 
	std::tie(answer,picked) = calcMinCoins(coins2, 10);
	std::cout << answer << ",";
	printPicked();
//	for(auto p : picked) std::cout << p << ",";
	std::cout << std::endl; 
	std::tie(answer,picked) = calcMinCoins(coins2, 23);
	std::cout << answer << ",";
	printPicked();
//	for(auto p : picked) std::cout << p << ",";
	std::cout << std::endl; 
	std::tie(answer,picked) = calcMinCoins(coins2, 32);
	std::cout << answer << ",";
	printPicked();
//	for(auto p : picked) std::cout << p << ",";
	std::cout << std::endl; 

	return 0;
}

