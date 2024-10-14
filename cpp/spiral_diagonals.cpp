#include <iostream>
using namespace std;

int spiral_diagonals(int count){
    int sum = 1;
    int num = 1;
    int add = 2;
    for (int k = 1; k <= count / 2; k++, add += 2){
        for (int j = 1; j <= 4; j++){
            num += add;
            sum += num;
        }
    }
    return sum;
}

int main(){
    cout << spiral_diagonals(5) << endl;
    cout << spiral_diagonals(101) << endl;
    cout << spiral_diagonals(1001) << endl;

    return 0;
}
