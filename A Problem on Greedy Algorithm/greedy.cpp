#include<algorithm>
#include<iostream>

using namespace std;

int main() {
    int noOfFriends, noOfPlants, minimumCost = 0;
    cin >> noOfPlants >> noOfFriends;

    int prices[noOfPlants];

    for(int i = 0; i < noOfPlants; i++) {
        cin >> prices[i];
    }

    sort(prices, prices + noOfPlants, greater<int>());

    for(int i = 0; i < noOfPlants; i++) {
        int multiply = i / noOfFriends + 1;

        minimumCost += prices[i] * multiply;
    }

    cout << minimumCost << endl;
}