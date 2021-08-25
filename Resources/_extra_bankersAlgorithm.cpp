#include <iostream>
#include <queue>
using namespace std;

class Process
{
public:
    int pid;
    int allocated[3]; // A, B and C
    int maxNeed[3];
    int need[3];
};

int main()
{
    const int N = 5; // number of processes
    Process p[N];    // 5 processes
    cout << "Enter the allocated resources and max need for:\n";
    for (int i = 0; i < N; i++)
    {
        cout << "P" << i << "\n";
        p[i].pid = i;

        cout << "Allocated: ";
        for (int j = 0; j < 3; j++)
        {
            cin >> p[i].allocated[j];
        }
        cout << "Max needed: ";
        for (int j = 0; j < 3; j++)
        {
            cin >> p[i].maxNeed[j];
        }
        cout << endl;
    }

    // calculate need
    for (int i = 0; i < N; i++)
        for (int j = 0; j < 3; j++)
            p[i].need[j] = p[i].maxNeed[j] - p[i].allocated[j];

    queue<Process> q;
    vector<Process> safe_sequence;
    int available[3] = {2, 1, 0};

    int deadlockCounter = 0; // if our available array doesn't change for N times
    // it is a deadlock !

    for (int i = 0; i < N; i++) // load all the process to be executed
        q.push(p[i]);

    cout << "Initially available: ";
    for (int i = 0; i < 3; i++)
                cout << available[i] << " ";
    cout << "\n";
    cout << "Processes      Max Needed      Need    Available\n";

    while (!q.empty())
    {
        Process current = q.front();
        q.pop();
        bool completed = true;
        for (int i = 0; i < 3; i++)
        {
            if (current.need[i] > available[i])
            {
                completed = false;
                deadlockCounter++;
                break;
            }
        }
        // if this process can be completed
        if (completed)
        {
            // print the data and do calclations
            cout << "   P" << current.pid << "           ";
            for (int i = 0; i < 3; i++)
                cout << current.maxNeed[i] << " ";
            cout << "        ";
            for (int i = 0; i < 3; i++)
                cout << current.need[i] << " ";
            
            // calculating
            for (int i = 0; i < 3; i++) // then complete it and take back allocated resources
                available[i] += current.allocated[i];
            
            cout << "     ";
            for (int i = 0; i < 3; i++)
                cout << available[i] << " ";
            cout << endl;
            

            safe_sequence.push_back(current);
            deadlockCounter = 0;
        }
        else
        {
            if (deadlockCounter == N)
            {
                cout << "!!! DEADLOCK !!!\n";
                break;
            }
            else
                q.push(current);
        }
    }

    if (deadlockCounter != N)
    {
        cout << "Safe Sequence: P"<< safe_sequence[0].pid;
        for (int i = 1; i < N; i++)
            cout << " -> P" << safe_sequence[i].pid;

        cout << "\nTotal resources available: ";
        for (int i = 0; i < 3; i++)
            cout << available[i] << " ";
    }
    cout << endl;
    return 0;
}