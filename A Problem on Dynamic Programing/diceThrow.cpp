#include<iostream>
#include<fstream>

#define MOD 1000000007

using namespace std;

int noOfWaysOfGettingASum(int* faces, int n, int sum) {
    // In the table below, row number indicates (number of dices thrown - 1)
    // Column number indicates sum
    int dpTable[n][sum + 1];

    // As the base case is the throwing of 1 dice, it has to be initialized manually.
    for(int i = 1; i < sum + 1; i++) {
        dpTable[0][i] = i <= faces[0] ? 1 : 0;
    }

    dpTable[0][0] = 0;

    for(int i = 1; i < n; i++) {
        // There is no choice that we can make to get a sum 0. So, initializing the 0th column to be 0.
        dpTable[i][0] = 0;

        for(int j = 1; j <= sum; j++) {
            dpTable[i][j] = (dpTable[i][j - 1] + dpTable[i - 1][j - 1]) % MOD;

            if(j - faces[i] > 0) {
                dpTable[i][j] = (((dpTable[i][j] - dpTable[i - 1][j - faces[i] - 1]) % MOD) + MOD) % MOD;
            }
        }
    }

    return dpTable[n - 1][sum];
}

int main()
{
    int n, sum;
    ifstream input;
    input.open("test.txt");

    input >> n >> sum;

    int* faces = new int[n];

    for(int i = 0; i < n; i++) {
        input >> faces[i];
    }

    input.close();

    cout << noOfWaysOfGettingASum(faces, n, sum) << endl;
}
