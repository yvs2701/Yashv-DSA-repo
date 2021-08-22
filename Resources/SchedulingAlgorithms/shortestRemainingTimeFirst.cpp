#include <iostream>
using namespace std;

class process
{
    public:
    int pid, at, bt, ft = 0, wt = 0, tat = 0;
};

void bubbleSort(process p[], int n)
{
	// sorts according to arrival times
	// then according to burst times (since we need shortest job)
	for (int i = 0; i < n - 1; i++)
		for (int j = 0; j < n - i - 1; j++)
		{
			if (p[j].at > p[j + 1].at)
				std::swap(p[j], p[j + 1]);
			else if (p[j].at == p[j+1].at && p[j].bt > p[j].bt)
				std::swap(p[j], p[j+1]);
		}
}

process* shortestJob(process p[], int n, int currentTime)
{	
	// process* ptr = &p[0];
	// store the max bt process in ptr
	// since later on in 2nd loop (p[i].bt != 0) && (p[i].bt < ptr->bt)
	// may evaluate to false, since ptr points to a process with 0 bt
	// say all the processes with non 0 bt have p[i].bt > 0 then algorithm will break
	int maxIndex = 0;
	for (int i = 0; i < n; i++)
	{
		if (p[i].bt > p[maxIndex].bt)
		maxIndex = i;
	}
	process* ptr = &p[maxIndex];

	for (int i = 0; i < n; i++)
	{
		if (currentTime < p[i].at)
			break;
		if ((p[i].bt != 0) && (p[i].bt < ptr->bt))
			ptr = &p[i];
	}
	if (ptr->bt == 0) // i.e. ptr = &p[0], i.e. no change
		return nullptr;
	return ptr;
}

void findFinishTime(process p[], int n)
{
    int currentTime = p[0].at;
    int index = 0;

	process copy_process[n];
	for (int i = 0; i < n; i++)
		copy_process[i] = p[i];

    cout << "\n\n---------Gantt Chart---------\n" << currentTime;
	while (true)
	{
		process* ptr = shortestJob(copy_process, n, currentTime);
		if (ptr == nullptr)
			break;
		if (ptr->bt >= 1)
		{
			ptr->bt--;
			currentTime += 1;
			ptr->ft = currentTime;
			cout << "...[P" << ptr->pid << "]..." << currentTime;
		}
	}
    cout << "\n-----------------------------\n" << endl;
	// ptr stored the reference of the processes hence values are changed 
	// in copy_process's elements too
	for (int i = 0; i < n; i++)
		p[i].ft = copy_process[i].ft;
}

void findTurnAroundTime(process p[], int n)
{
	for (int i = 0; i < n; i++)
		p[i].tat = p[i].ft - p[i].at;
}

void findWaitingTime(process p[], int n)
{
	// calculating waiting time
	for (int i = 0; i < n; i++)
		p[i].wt = p[i].tat - p[i].bt;
}

void findAvgTime(process p[], int n)
{
    int total_wt = 0, total_tat = 0;
    for (int i = 0; i < n; i++)
        p[i].ft = 0;

    bubbleSort(p, n);

	findFinishTime(p, n);
	findTurnAroundTime(p, n);
	findWaitingTime(p, n);

    // printing
	cout << "Processes " << " Arrival Time " << " Burst time " << " Turn around time " << " Waiting time\n";
    for (int i = 0; i < n; i++)
	{
		total_wt += p[i].wt;
		total_tat += p[i].tat;
		cout << "   P" << p[i].pid << "             " << p[i].at << "           " << p[i].bt
			 << "           " << p[i].tat << "                   " << p[i].wt << endl;
	}

	cout << "Average waiting time = " << (float)total_wt / (float)n;
	cout << "\nAverage turn around time = " << (float)total_tat / (float)n;
    cout << endl;
}

int main()
{
    process p[5];
    p[0].pid = 1; p[0].at = 0; p[0].bt = 4;
    p[1].pid = 2; p[1].at = 1; p[1].bt = 2;
    p[2].pid = 3; p[2].at = 2; p[2].bt = 1;
    p[3].pid = 4; p[3].at = 3; p[3].bt = 5;
    p[4].pid = 5; p[4].at = 4; p[4].bt = 2;

    findAvgTime(p, sizeof(p)/sizeof(p[0]));
    return 0;
}