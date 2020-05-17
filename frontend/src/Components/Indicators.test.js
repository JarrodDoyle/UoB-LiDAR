import React from 'react';
import { PercentageIndicator } from './Indicators.js';
import renderer from 'react-test-renderer';

test('Percentage Indicator bad lower bound', () => {
  const component = renderer.create(
    <PercentageIndicator percentage={0}/>
  );
  let tree = component.toJSON();
  expect(tree).toMatchSnapshot();
});

test('Percentage Indicator bad upper bound', () => {
  const component = renderer.create(
    <PercentageIndicator percentage={59}/>
  );
  let tree = component.toJSON();
  expect(tree).toMatchSnapshot();
});

test('Percentage Indicator almost lower bound', () => {
  const component = renderer.create(
    <PercentageIndicator percentage={60}/>
  );
  let tree = component.toJSON();
  expect(tree).toMatchSnapshot();
});

test('Percentage Indicator almost upper bound', () => {
  const component = renderer.create(
    <PercentageIndicator percentage={99}/>
  );
  let tree = component.toJSON();
  expect(tree).toMatchSnapshot();
});

test('Percentage Indicator okay', () => {
  const component = renderer.create(
    <PercentageIndicator percentage={100}/>
  );
  let tree = component.toJSON();
  expect(tree).toMatchSnapshot();
});
