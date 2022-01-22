#include <iostream>
using namespace std;

class node // binary tree node
{
public:
    int data;
    node *left;
    node *right;

    node()
    {
        data = 0;
        left = nullptr;
        right = nullptr;
    }
    node(int value)
    {
        data = value;
        left = nullptr;
        right = nullptr;
    }
};

void leftChild(node *head, int value)
{
    if (head->left != nullptr)
    {
        cout << "Left child already exists!" << endl;
        return;
    }
    node *newNode = new node(value);
    head->left = newNode;
}
void rightChild(node *head, int value)
{
    if (head->right != nullptr)
    {
        cout << "Right child already exists!" << endl;
        return;
    }
    node *newNode = new node(value);
    head->right = newNode;
}

// skewed binary tree has only one child per node
bool isSkewed(node *node)
{
    // if we reach the leaf node that means every node in between had only child
    // otheriwse we would've returned false already
    // hence reaching the leaf means the tree is skewed
    if (node->left == nullptr && node->right == nullptr)
        return true;

    // if we come here then we are not at the leaf node
    if (node->left == nullptr)
        return isSkewed(node->right);
    if (node->right == nullptr)
        return isSkewed(node->left);

    // else node has 2 children
    return false;
}

int main()
{
    node *head = new node(1);              //       1
    leftChild(head, 2);                    //     2
    leftChild(head->left, 3);              //   3
    rightChild(head->left->left, 4);       //     4
    leftChild(head->left->left->right, 5); //   5

    isSkewed(head) ? cout << "Tree is skewed\n" : cout << "Not skewed\n"; // output: Tree is skewed
    leftChild(head->left->left, 0);
    isSkewed(head) ? cout << "Tree is skewed" : cout << "Not skewed"; // output: Not Skewed
    cout << endl;
}