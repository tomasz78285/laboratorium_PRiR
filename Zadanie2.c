#include<stdio.h>
#include<sys/types.h>
#include<math.h>
#include<time.h>
#include<stdlib.h>

float potega(float x, float y)
{
        float wynik = 1;
        for(int i=1; i<=y; i++)
        {
                wynik *=x;
        }
        return wynik;
}


int main(){
int ret,p,n;
float pi, suma;
printf("Podaj p: ");
scanf("%d", &p);
ret = fork();
srand(time(NULL));

if(ret == 0)
{
   for(int i = 0; i<=p; i++){
	n = rand()%5000+100;
	for(int j=1; j<=n; j++)
	{
		suma += potega(-1,j-1)/(2*j-1);
	}
	pi = 4*suma;
	printf("%f", pi);
   }
}
else
{
   for(int i = 0; i<=p; i++){
	n = rand()%5000 + 100;
        for(int j=1; j<=n; j++)
        { 
                suma += potega((-1),(j-1))/(2*j-1);
        } 
        pi = 4*suma;
	printf("%f", pi);
	
   }
}

return 0;
}
