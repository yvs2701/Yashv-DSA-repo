#include <iostream>
#include <queue>
using namespace std;

class process
{
    public:
    int pid, at, bt, ft = 0, wt = 0, tat = 0;
};

void bubbleSort(process p[], int n)
{
	// sorts according to arrival times
	for (int i = 0; i < n - 1; i++)
		for (int j = 0; j < n - i - 1; j++)
			if (p[j].at > p[j + 1].at)
				std::swap(p[j], p[j + 1]);
}

void findFinishTime(process p[], int n, int TQ)
{
    int currentTime = p[0].at;

    queue<process> readyQ;
    readyQ.push(p[0]);

    int index = 0;
    bool didntComplete = false;

    cout << "\n\n---------Gantt Chart---------\n" << currentTime;
    while (!readyQ.empty())
    {
        process currentProcess = readyQ.front();
        // currentProcess is not a reference it is copy of the process
        // hence later we have to store the ft back to original p[i].ft
        readyQ.pop();

        if (currentProcess.bt > TQ)
        {
            currentProcess.bt -= TQ;
            currentTime += TQ;
            currentProcess.ft = currentTime;
            didntComplete = true; // process didn't complete
        }
        else
        {
            currentTime += currentProcess.bt;
            currentProcess.bt = 0;
            currentProcess.ft = currentTime;
            didntComplete = false; // process completed
        }

        for (int i = index; i < n; i++)
        {
            // all the processes which have arrived by this time
            // and their bt is unchanged ! (i.e. we haven't included them in queue yet)
            // push them to ready queue
            if ((p[i].at <= currentTime) && (currentProcess.pid != p[i].pid))
                readyQ.push(p[i]);
            else if (p[i].at > currentTime)
            {
                index = i;
                break;
            }
            if (i == n - 1)
                index = n;
        }
        if (didntComplete) // if current process didn't complete
            readyQ.push(currentProcess); // push it back to queue

        // updating the values in original process array
        for (int i = 0; i < n; i++)
        {
            if (p[i].pid == currentProcess.pid)
            {
                // store the ft back to original p[i].ft
                // since currentProcess was just a copy of processes store in readyQ
                p[i].ft = currentProcess.ft;
                cout << "...[P" << p[i].pid << "]..." << currentTime;
                break;
            }
        }
    }
    cout << "\n-----------------------------\n" << endl;
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

void findAvgTime(process p[], int n, int TQ)
{
    int total_wt = 0, total_tat = 0;
    for (int i = 0; i < n; i++)
        p[i].ft = 0;

    bubbleSort(p, n);

	findFinishTime(p, n, TQ);
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

    const int TimeQuantum = 2;
    findAvgTime(p, sizeof(p)/sizeof(p[0]), TimeQuantum);
    return 0;
}