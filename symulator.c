#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include "mpi.h"
#define REZERWA 500
#define POLE 1
#define START 2
#define PRACA 3
#define KONIEC_PRACY 4
#define AWARIA 5
#define uzupelnij 5000
int nawozy = 8000;
int LADUJ=1, NIE_LADUJ=0;
int liczba_procesow;
int nr_procesu;
int ilosc_traktorow;
int nr_traktora;
int ilosc_pol=4;
int ilosc_zasianych_pol=0;
int tag=1;
int wyjedz[2];
int zaladuj[2];
MPI_Status mpi_status;
void Wyjedz(int nr_samolotu, int stan) 
{
wyjedz[0]=nr_traktora;
wyjedz[1]=stan;
MPI_Send(&wyjedz, 2, MPI_INT, 0, tag, MPI_COMM_WORLD);
sleep(1);
}

void Pole(int liczba_procesow){
int nr_traktora,status;
ilosc_traktorow = liczba_procesow - 1;
if(rand()%2==1){
printf("Jest ladna pogoda. Nie ma blota mozna siac\n");
}
else{
printf("Pada a na polu jest bloto. Nie jedz siac bo ugrzezniesz\n");
}
printf("Trzeba zasiac %d pol\n", ilosc_pol);
sleep(2);
while(ilosc_pol<=ilosc_traktorow){
MPI_Recv(&zaladuj,2,MPI_INT,MPI_ANY_SOURCE,tag,MPI_COMM_WORLD, &mpi_status);
nr_traktora=zaladuj[0];
status=zaladuj[1];
if(status==1){
printf("Traktor %d jest na polu\n", nr_traktora);
}
if(status==2){
printf("W siewniku sa nawozy traktor %d moze jechac na pole nr %d\n", nr_traktora, ilosc_zasianych_pol);
ilosc_zasianych_pol--;
}
if(status==3){
printf("Traktor %d sieje\n", nr_traktora);
}
if(status==4){
if(ilosc_zasianych_pol<ilosc_pol){
ilosc_zasianych_pol++;
MPI_Send(&LADUJ, 1, MPI_INT, nr_traktora, tag, MPI_COMM_WORLD);
}
else{
MPI_Send(&NIE_LADUJ, 1, MPI_INT, nr_traktora, tag, MPI_COMM_WORLD);
}
}

if(status==5){
ilosc_traktorow--;
printf("Ilosc traktorow %d\n", ilosc_traktorow);
}
} 
printf("Program zakonczyl dzialanie:)\n");
}
void Traktor(){
int stan,suma,i;
stan=PRACA; 
while(1){
if(stan==1){
if(rand()%2==1){
stan=START;
nawozy=uzupelnij;
printf("Wyjezdza, traktor %d\n",nr_procesu);
Wyjedz(nr_procesu,stan);
}
else{
Wyjedz(nr_procesu,stan);
}
}
else if(stan==2){
printf("Wyjechalem, traktor %d\n",nr_procesu);
stan=PRACA;
Wyjedz(nr_procesu,stan);
}
else if(stan==3){
nawozy-=rand()%500; 
if(nawozy<=REZERWA){
stan=KONIEC_PRACY;
printf("Traktor wraca uzupelnic zbiornik\n");
Wyjedz(nr_procesu,stan);
}

else{
for(i=0; rand()%10000;i++);
}
}
else if(stan==4){
int temp;
MPI_Recv(&temp, 1, MPI_INT, 0, tag, MPI_COMM_WORLD, &mpi_status);
if(temp==LADUJ){
stan=POLE;
printf("Powrot, traktor %d\n", nr_procesu);
}
else
{
nawozy-=rand()%500;
if(nawozy>0){
Wyjedz(nr_procesu,stan);
}
else{
stan=AWARIA;
printf("Uszkodzony uklad hydrauliczny\n");
Wyjedz(nr_procesu,stan);
return;
}
}
}
}
}
int main(int argc, char *argv[])
{
MPI_Init(&argc, &argv);
MPI_Comm_rank(MPI_COMM_WORLD,&nr_procesu);
MPI_Comm_size(MPI_COMM_WORLD,&liczba_procesow);
srand(time(NULL));
if(nr_procesu == 0)
Pole(liczba_procesow);
else 
Traktor();
MPI_Finalize();
return 0;
}
