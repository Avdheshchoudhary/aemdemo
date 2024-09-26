import React, { useState } from 'react';
import { MapTo } from '@adobe/aem-react-editable-components';
import './Calculator.css'

// Configuration for the Calculator component
const CalculatorConfig = {
    emptyLabel: "Calculator",
    isEmpty: function (props) {
        return !props || (!props.input1 && !props.input2);
    },
};

// Calculator React component
const Calculator = ({ input1 = '', input2 = '' }) => {
    const [inputValue1, setInputValue1] = useState(input1);
    const [inputValue2, setInputValue2] = useState(input2);
    const [result, setResult] = useState(null);
    const [operation, setOperation] = useState('');

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        if (name === 'input1') {
            setInputValue1(value);
        } else if (name === 'input2') {
            setInputValue2(value);
        }
    };

    const performOperation = (op) => {
        const num1 = parseFloat(inputValue1);
        const num2 = parseFloat(inputValue2);

        if (isNaN(num1) || isNaN(num2)) {
            setResult('Invalid input');
            return;
        }

        let res;
        switch (op) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                if (num2 === 0) {
                    setResult('Cannot divide by zero');
                    return;
                }
                res = num1 / num2;
                break;
            case '%':
                res = (num1 * num2) / 100;
                break;
            default:
                setResult('Unknown operation');
                return;
        }

        setResult(res);
        setOperation(op);
    };

    return (
        <div className="calculator">
            <input
                type="number"
                name="input1"
                value={inputValue1}
                onChange={handleInputChange}
                placeholder="Enter first number"
            />
            <input
                type="number"
                name="input2"
                value={inputValue2}
                onChange={handleInputChange}
                placeholder="Enter second number"
            />
            <div className="buttons">
                <button onClick={() => performOperation('+')}>+</button>
                <button onClick={() => performOperation('-')}>-</button>
                <button onClick={() => performOperation('*')}>*</button>
                <button onClick={() => performOperation('/')}>/</button>
                <button onClick={() => performOperation('%')}>%</button>
            </div>
            {result !== null && (
                <div className="result">
                    <p>
                        {inputValue1} {operation} {inputValue2} = {result}
                    </p>
                </div>
            )}
        </div>
    );
};

// MapTo function to connect with AEM
export default MapTo("aem-sites-react/components/calculator")(Calculator, CalculatorConfig);
