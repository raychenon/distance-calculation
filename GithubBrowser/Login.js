'use strict';

var buffer = require('buffer');
import React, { Component } from 'react';
import {
	ActivityIndicator,
	Image,
	StyleSheet,
  Text,
	TextInput,
	TouchableHighlight,
  View,
} from 'react-native';

class Login extends Component{

	constructor(props){
			super(props);

			this.state = {
				showProgress: false
			}
	}

	render(){

		var errorCtrl = <View />;
		if(!this.state.success && this.state.badCredentials){
			errorCtrl = <Text style={styles.error}>
				The distance calculation did not work
			</Text>;
		}
		if(!this.state.success && this.state.unknownError){
			errorCtrl = <Text style={styles.error}>
				We experienced an unexpected issue
			</Text>;
		}
		if(this.state.success){
			errorCtrl = <Text style={styles.success}>
				Authentication successful
			</Text>;
		}


		return(
				<View style={styles.container}>
					<Text style={styles.heading}>Distance calculation</Text>
					<TextInput
						onChangeText={(text)=> this.setState({username: text})}
						style={styles.input}
						placeholder="Start destination">
					</TextInput>
					<TextInput
						onChangeText={(text)=> this.setState({password: text})}
						style={styles.input}
						placeholder="End destination">
					</TextInput>
					<TouchableHighlight
						onPress={this.onLoginPressed.bind(this)}
						style={styles.button}>
						<Text style={styles.buttonText}>Log in</Text>
					</TouchableHighlight>
					{errorCtrl}
					<ActivityIndicator
						animating={this.state.showProgress}
						size="large"
						style={styles.loader}/>
				</View>
			);
	}

	onLoginPressed(){
		console.log('Attempting to log in with username ' + this.state.username );
		this.setState({showProgress: true});

		var authService = require('./AuthService');
		authService.login({
			username: this.state.username,
			password: this.state.password
		},(results)=>{
			this.setState(Object.assign({
				showProgress: false
			},results));

			if(results.success && this.props.onLogin){
				this.props.onLogin();
			}
		});
	}

}

var styles = StyleSheet.create({
	container: {
		backgroundColor: '#F5FCFF',
		flex: 1,
		paddingTop: 40,
		padding: 10,
		alignItems: 'center'
	},
	heading: {
		fontSize:30,
		marginTop: 10
	},
	input: {
		height: 50,
		alignSelf: 'stretch',
		justifyContent: 'center',
		marginTop: 10,
		padding: 4,
		fontSize: 18,
		borderWidth: 1,
		borderColor: '#48bbec'
	},
	button: {
		height: 50,
		backgroundColor: '#48BBEC',
		borderColor: '#48BBEC',
		alignSelf: 'stretch',
		marginTop: 10,
		justifyContent: 'center',
		borderRadius: 5
	},
	buttonText: {
		fontSize: 22,
		color: '#FFF',
		alignSelf: 'center'
	},
	loader: {
		marginTop: 20
	},
	error: {
		marginTop: 10,
		color: 'red'
	},
	success: {
		marginTop: 10,
		color: 'green'
	}
});

module.exports = Login;
