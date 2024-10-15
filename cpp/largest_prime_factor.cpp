#include <iostream>
#include <cmath>
using namespace std;

bool is_prime(long long int num){
    for (long long int i = 2; i <= sqrt(num); i++){
        if (num % i == 0){
            return false;
        }
    }
    return true;
}

long long int largest_prime_factors(long long int num){
    long long int max = 0;
    long long int d;
    for (long long int i = 2; i <= num / 2; i++){
        if (num % i == 0){
            d = num / i;
            if (is_prime(d)){
                return d;
            }
        }
    }
    return max;
}

int main()
{
    cout << largest_prime_factors(12) << endl;
    cout << largest_prime_factors(13195) << endl;
    cout << largest_prime_factors(600851475143) << endl;
    return 0;
}