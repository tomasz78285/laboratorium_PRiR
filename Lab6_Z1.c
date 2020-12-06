#include <stdio.h>
#include "mpi.h"
#include <math.h>

int main(int argc, char **argv){
	int p_numer;		
	int n, p;
	int tag = 10;
	double pi;
	
	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &p_numer);
	MPI_Comm_size(MPI_COMM_WORLD, &n);
	MPI_Status status;

	if(p_numer == 0){
		p = p_numer + 1;
		pi = (pow(-1, p - 1) / ((2 * p) - 1)) * 4;
		MPI_Send(&pi, 1, MPI_DOUBLE, p_numer + 1, tag, MPI_COMM_WORLD);
	}
	if((p_numer > 0) && (p_numer < n)){
		MPI_Recv(&pi, 1, MPI_DOUBLE, p_numer - 1, tag, MPI_COMM_WORLD, &status);
		p = p_numer + 1;
		pi /= 4;
		pi += (pow(-1, p - 1) / ((2 * p) - 1));
		pi *= 4;
		printf("Proces: %d, przyblizenie: %f\n", p_numer, pi);
		if(p_numer != n - 1){
			MPI_Send(&pi, 1, MPI_DOUBLE, p_numer + 1, tag, MPI_COMM_WORLD);
		}
	}
	MPI_Finalize();
	return 0;
}
