// TodoList.js
'use client';
import React, { useState } from 'react';

function TodoList() {
  const [todos, setTodos] = useState<string[]>([]);
  const [input, setInput] = useState('');

  const addTodo = () => {
    if (input.trim() !== '') {
      setTodos([...todos, input]);
      setInput('');
    }
  };

  const deleteTodo = (index:number) => {
    const newTodos = todos.filter((_, i) => i !== index);
    setTodos(newTodos);
  };

  return (
    <div>
      <h1>Todo List</h1>
      <input
        type="text"
        value={input}
        onChange={(e) => setInput(e.target.value)}
        placeholder="새 할 일을 입력하세요"
      />
      <button onClick={addTodo}>추가</button>
      <ul>
        {todos.map((todo, index) => (
          <li key={index}>
            {todo} <button className="bg-red-300" onClick={() => deleteTodo(index)}>삭제</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TodoList;