#include <iostream>
#include <algorithm>
using namespace std;

void findFinishTime(int n, int bt[], int ft[])
{
	ft[0] = bt[0];
	for (int i = 1; i < n; i++)
		ft[i] = ft[i - 1] + bt[i];
}

void findTurnAroundTime(int n, int at[], int ft[], int tat[])
{
	for (int i = 0; i < n; i++)
		tat[i] = ft[i] - at[i];
}

void findWaitingTime(int n, int bt[], int tat[], int wt[])
{
	// calculating waiting time
	for (int i = 0; i < n; i++)
		wt[i] = tat[i] - bt[i];
}

void bubbleSort(int at[], int bt[], int p[], int n)
{
	// sorts according to arrival times
	for (int i = 0; i < n - 1; i++)
		for (int j = 0; j < n - i - 1; j++)
			if (at[j] > at[j + 1])
			{
				std::swap(at[j], at[j + 1]);
				std::swap(bt[j], bt[j + 1]);
				std::swap(p[j], p[j + 1]);
			}
}

void findavgTime(int processes[], int n, int bt[], int at[])
{
	int wt[n], tat[n], ft[n] /*finish time*/, total_wt = 0, total_tat = 0;

	bubbleSort(at, bt, processes, n);

	findFinishTime(n, bt, ft);
	findTurnAroundTime(n, at, ft, tat);
	findWaitingTime(n, bt, tat, wt);
	
	// printing
	cout << "Processes " << " Arrival Time " << " Burst time " << " Turn around time " << " Waiting time\n";
	for (int i = 0; i < n; i++)
	{
		total_wt = total_wt + wt[i];
		total_tat = total_tat + tat[i];
		cout << "   P" << processes[i] << "             " << at[i] << "           " << bt[i]
			 << "           " << tat[i] << "              " << wt[i] << endl;
	}

	cout << "Average waiting time = " << (float)total_wt / (float)n;
	cout << "\nAverage turn around time = " << (float)total_tat / (float)n;

	cout << "\n\n---------Gantt Chart---------\n";
	cout << at[0] << "...[P" << processes[0] << "]..." << ft[0];
	for (int i = 1; i < n - 1; i++)
	{
		cout << "...[P" << processes[i] << "]..." << ft[i] << "...";
	}
	cout << "[P" << processes[n - 1] << "]..." << ft[n - 1];
	cout << "\n-----------------------------\n" << endl;
}

int main()
{
	int processes[] = {1, 2, 3, 4, 5};
	int n = sizeof(processes) / sizeof(processes[0]);

	int burst_time[] = {4, 2, 1, 5, 2};
	int arival_time[] = {0, 1, 2, 3, 4};
	findavgTime(processes, n, burst_time, arival_time);

	return 0;
}