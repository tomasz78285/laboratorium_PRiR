#include<stdio.h>
#include<stdlib.h>
#include<sys/types.h>
#include<time.h>

float fx(float x)
{
return 4*x-6*x+5;
}

int main()
{
int a, b, n, i, j, p, ret;
srand(time(NULL));
printf("Podaj p: ");
scanf("%d", &p);
ret = fork();
if(ret == 0)
{
for(i=0; i<=p; i++)
{
	a = rand()%10;
	b = rand()%10+10;
	n = rand()%10;
	float calka = 0;
	float h = (b-a)/n;
	for(int j=1; j<= n-1; j++){calka+=fx(a+1*h);}
	calka += (fx(a) + fx(b)) / 2;
	calka *= h;
	printf("%f", calka);
}
}
else
{
for(i=0; i<=p; i++)
{ 
	a = rand()%10;
	b = rand()%10+10;
	n = rand()%10;
        float calka = 0;
        float h = (b-a)/n;
        for(int j=1; j<= n-1; j++){calka+=fx(a+1*h);}
        calka += (fx(a) + fx(b)) / 2;
        calka *= h;
        printf("%f", calka);
}

}
return 0;
}
