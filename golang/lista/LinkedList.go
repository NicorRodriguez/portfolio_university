package main

import (
	"errors"
)

type ILinkedList interface {
	insert(node interface{}) error
	search(ll *LinkedList, label string) (interface{}, error)
	delete(ll *LinkedList, label string) bool
	size() int
	isEmpty(ll *LinkedList) bool
}

type LinkedList struct {
	head   *Node
	llsize int
}

type Node struct {
	data  interface{}
	label string
	next  *Node
}

func (ll *LinkedList) insert(node *Node) error {
	if node == nil || node.next != nil {
		return errors.New("Empty node or tried to insert a list.")
	}
	if ll.head == nil {
		ll.head = node
	} else {
		node.next = ll.head
		ll.head = node
	}
	ll.llsize++
	return nil
}

func (ll *LinkedList) search(label string) (interface{}, error) {
	if ll.head == nil {
		return nil, errors.New("Empty list.")
	}
	actual := ll.head
	for actual != nil {
		if actual.label == label {
			return actual, nil
		}
	}

	return nil, nil
}

func (ll *LinkedList) delete(label string) bool {
	actual := ll.head

	if actual == nil {
		return false
	}
	if actual.label == label {
		ll.head = ll.head.next
		ll.llsize--
		return true
	}

	for actual != nil {
		if actual.next.label == label {
			actual.next = actual.next.next
			ll.llsize--
			return true
		}
		actual = actual.next
	}

	return true
}

func (ll *LinkedList) size() int {
	return ll.llsize
}

func (ll *LinkedList) isEmpty() bool {
	return ll.head == nil
}
