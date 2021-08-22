#include <iostream>
#include <algorithm>
using namespace std;

// in the SRTF code I've used a better technique of finding the Shortest Job
// (refer to that too)
void sortBt(int n, int at[], int bt[], int p[], int currentTime, int indexFrom)
{
	// sorts according to burst times 
	// only those elements with at < currentTime, and whose 
	// index > indexFrom (since they will be already executed by the cpu)
	int sortTill = -1;
	// find till where we need to sort
	for (int i = indexFrom; i < n; i++)
		if (at[i] > currentTime)
		{
			sortTill = i + 1; // just taking the length which is 1 + index
			break;
		}
	if (sortTill == -1) // all elements have at <= current time
		sortTill = n;

	// sort them
	// inner loop isn't running till sortTill - i - 1
	// since we know for sure indexFrom can never = 0 ! (analyze above code for why ?)
	for (int i = indexFrom; i < sortTill - 1; i++)
		for (int j = indexFrom; j < sortTill - i; j++)
			if (bt[j] > bt[j + 1])
			{
				std::swap(at[j], at[j + 1]);
				std::swap(bt[j], bt[j + 1]);
				std::swap(p[j], p[j + 1]);
			}
}

void findFinishTime(int n, int bt[], int ft[], int at[], int p[])
{
	// calculates finishing times and prints gantt chart

	ft[0] = bt[0];
	// 0th index
	cout << "\n\n---------Gantt Chart---------\n";
	cout << at[0] << "...[P" << p[0] << "]..." << ft[0];

	for (int i = 1; i < n; i++)
	{
		sortBt(n, at, bt, p, ft[i-1], i);
		// by now we have bt[i] as the shortest job !
		ft[i] = ft[i-1] + bt[i];
		cout << "...[P" << p[i] << "]..." << ft[i];
	}
	cout << "\n-----------------------------\n" << endl;
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
	// and then burst times (only when arrival time is same)
	for (int i = 0; i < n - 1; i++)
		for (int j = 0; j < n - i - 1; j++)
		{
			if (at[j] > at[j + 1])
			{
				std::swap(at[j], at[j + 1]);
				std::swap(bt[j], bt[j + 1]);
				std::swap(p[j], p[j + 1]);
			}
			else if (at[j] == at[j+1])
			{
				if (bt[j] > bt[j+1])
				{
					std::swap(at[j], at[j + 1]);
					std::swap(bt[j], bt[j + 1]);
					std::swap(p[j], p[j + 1]);
				}
			}
		}
}

void findavgTime(int processes[], int n, int bt[], int at[])
{
    int wt[n], tat[n], ft[n] /*finish time*/, total_wt = 0, total_tat = 0;

    bubbleSort(at, bt, processes, n);

	findFinishTime(n, bt, ft, at, processes);
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
	cout << endl;
}

int main()
{
	int processes[] = {1, 2, 3, 4, 5};
	int n = sizeof(processes) / sizeof(processes[0]);

	int burst_time[] = {4, 2, 1, 5, 2};
	int arrival_time[] = {0, 1, 2, 3, 4};
	findavgTime(processes, n, burst_time, arrival_time);

	return 0;
}