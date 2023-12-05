package main

import (
	"errors"
)

type IPila interface {
	push(node interface{})
	pop(label string) interface{}
	peek(label string) bool
	size() int
	isEmpty() bool
}

type LinkedList struct {
	head   *Node
	tail   *Node
	llsize int
}

type Node struct {
	data     interface{}
	label    string
	next     *Node
	previous *Node
}

func (ll *LinkedList) push(node *Node) error {
	if node == nil || node.next != nil {
		return errors.New("Empty node or tried to insert a list.")
	}
	if ll.head == nil {
		ll.head = node
		ll.tail = node
	} else {
		node.next = ll.head
		ll.head = node
	}
	ll.llsize++
	return nil
}

func (ll *LinkedList) pop() *Node {
	actual := ll.tail

	if actual == nil {
		return nil
	}

	if ll.tail == ll.head {
		ll.head = nil
		ll.tail = nil
	} else {
		ll.tail = ll.tail.previous
	}

	ll.llsize--

	return actual
}

func (ll *LinkedList) peek() *Node {
	return ll.tail
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

func (ll *LinkedList) size() int {
	return ll.llsize
}

func (ll *LinkedList) isEmpty() bool {
	return ll.head == nil
}
