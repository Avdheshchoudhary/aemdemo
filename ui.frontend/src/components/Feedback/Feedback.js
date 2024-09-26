// import React from 'react';
// import { useForm, Controller } from 'react-hook-form';
// import { Form, Button, Col, Row } from 'react-bootstrap';
// import { MapTo } from '@adobe/aem-react-editable-components';
// import 'bootstrap/dist/css/bootstrap.min.css';

// // Configuration for the FeedbackForm component
// const FeedbackConfig = {
//     emptyLabel: "Feedback Form",
//     isEmpty: function (props) {
//         return !props || (!props.name && !props.age && !props.address && !props.description && !props.rating);
//     },
// };

// // FeedbackForm React component
// const Feedback = ({ name = '', age = '', address = '', description = '', rating = '' }) => {
//     const { control, handleSubmit, register, formState: { errors } } = useForm({
//         defaultValues: { name, age, address, description, rating }
//     });
    
//     const onSubmit = data => {
//         console.log(data);
//         // Handle form submission (e.g., send data to a server)
//     };

//     return (
//         <Form onSubmit={handleSubmit(onSubmit)}>
//             <Form.Group as={Row} controlId="formName">
//                 <Form.Label column sm={2}>Name</Form.Label>
//                 <Col sm={10}>
//                     <Form.Control 
//                         type="text" 
//                         placeholder="Enter your name" 
//                         {...register('name', { required: 'Name is required' })} 
//                     />
//                     {errors.name && <Form.Text className="text-danger">{errors.name.message}</Form.Text>}
//                 </Col>
//             </Form.Group>

//             <Form.Group as={Row} controlId="formAge">
//                 <Form.Label column sm={2}>Age</Form.Label>
//                 <Col sm={10}>
//                     <Form.Control 
//                         type="number" 
//                         placeholder="Enter your age" 
//                         {...register('age', { required: 'Age is required', min: 1 })} 
//                     />
//                     {errors.age && <Form.Text className="text-danger">{errors.age.message}</Form.Text>}
//                 </Col>
//             </Form.Group>

//             <Form.Group as={Row} controlId="formAddress">
//                 <Form.Label column sm={2}>Address</Form.Label>
//                 <Col sm={10}>
//                     <Form.Control 
//                         type="text" 
//                         placeholder="Enter your address" 
//                         {...register('address', { required: 'Address is required' })} 
//                     />
//                     {errors.address && <Form.Text className="text-danger">{errors.address.message}</Form.Text>}
//                 </Col>
//             </Form.Group>

//             <Form.Group as={Row} controlId="formDescription">
//                 <Form.Label column sm={2}>Description</Form.Label>
//                 <Col sm={10}>
//                     <Form.Control 
//                         as="textarea" 
//                         rows={3} 
//                         placeholder="Enter your feedback" 
//                         {...register('description', { required: 'Description is required' })} 
//                     />
//                     {errors.description && <Form.Text className="text-danger">{errors.description.message}</Form.Text>}
//                 </Col>
//             </Form.Group>

//             <Form.Group as={Row} controlId="formRating">
//                 <Form.Label column sm={2}>Rating</Form.Label>
//                 <Col sm={10}>
//                     <Controller
//                         name="rating"
//                         control={control}
//                         defaultValue={rating}
//                         render={({ field }) => (
//                             <Form.Control
//                                 as="select"
//                                 {...field}
//                             >
//                                 <option value="">Select rating</option>
//                                 {[1, 2, 3, 4, 5].map(rating => (
//                                     <option key={rating} value={rating}>{rating}</option>
//                                 ))}
//                             </Form.Control>
//                         )}
//                         rules={{ required: 'Rating is required' }}
//                     />
//                     {errors.rating && <Form.Text className="text-danger">{errors.rating.message}</Form.Text>}
//                 </Col>
//             </Form.Group>

//             <Button variant="primary" type="submit">Submit</Button>
//         </Form>
//     );
// };

// // MapTo function to connect with AEM
// export default MapTo("aem-sites-react/components/feedback")(Feedback, FeedbackConfig);
