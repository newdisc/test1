import React, { useEffect, useReducer } from 'react';
import './ClassDefinitionView.css';
import { getAllClassDefinition } from './fetch'
import { classDefinitionReducer, initialState } from './reducer'
import {ClassDefinition} from './type'
import { Button, Card, Elevation, Toast, Toaster, HotkeysProvider, HTMLTable, Icon, IconSize } from "@blueprintjs/core";


function ClassDefinitionView(){
    const [state, dispatch] = useReducer(classDefinitionReducer, initialState)
    const getData = () => {getAllClassDefinition(dispatch)}
    useEffect(getData,[]);
    return (
        <div className="ClassDefinitionView">
            <Toaster>
                {!!state.error ?(<Toast message={state.error} />) : null}
            </Toaster>
            <Card elevation={Elevation.TWO}>
                <h5>Class Definition</h5>
                <HotkeysProvider>
                <HTMLTable bordered condensed striped>
                    <thead>
                        <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Description</th>
                        </tr>
                    </thead>
                    <tbody>
                        {state.classes.map((c:ClassDefinition) => (
                        <tr key={c.name}>
                            <td>#</td>
                            <td>{c.name}</td>
                            <td>{c.description}</td>
                        </tr>
                        ))}
                    </tbody>
                    </HTMLTable>
                    </HotkeysProvider>
                    <Button minimal small onClick={getData}> 
                        <Icon icon='refresh' size={IconSize.STANDARD}/>
                    </Button>
                    <Button minimal small>
                        <Icon icon='download' size={IconSize.STANDARD}/>
                    </Button>
                    <Button minimal small>
                        <Icon icon='upload' size={IconSize.STANDARD}/>
                    </Button>
            </Card>
        </div>
    );
}

export default ClassDefinitionView;

/* FcDownload
            {console.log("Render: ", state)}
            {state.error}
            {state.pending?"true":"false"}
            {state.classes.map((cd: ClassDefinition) => (
                <div key={cd.name}>{cd.name}<br/>{cd.description}</div>
            ))}

                    <Card.Title>Special title treatment</Card.Title>
                    <Card.Text>
                    With supporting text below as a natural lead-in to additional content.
                    </Card.Text>

            <Alert show={!!state.error} variant="error" >
                <Alert.Heading>Issue working with ClassDefinitions</Alert.Heading>
                <p>{state.error}</p>
            </Alert>


*/